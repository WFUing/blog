package com.learning.dataflow;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * @Author jyj
 * @Date 2023/6/2 15:05
 */
public class HolidayRequest {
    public static void main(String[] args) {

        /*
         * 首先要做的是初始化ProcessEngine流程引擎实例。
         * 这是一个线程安全的对象，因此通常只需要在一个应用中初始化一次。 ProcessEngine由ProcessEngineConfiguration实例创建。
         * 该实例可以配置与调整流程引擎的设置。 通常使用一个配置XML文件创建ProcessEngineConfiguration，但是（像在这里做的一样）也可以编程方式创建它。
         * ProcessEngineConfiguration所需的最小配置，是数据库JDBC连接
         * */
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        /*
         * 然后使用这个配置创建ProcessEngine对象（第17行）。
         * */
        ProcessEngine processEngine = cfg.buildProcessEngine();

        /*
         * 将流程定义部署至Flowable引擎，需要使用RepositoryService，其可以从ProcessEngine对象获取。
         * 使用RepositoryService，可以通过XML文件的路径创建一个新的部署(Deployment)，并调用deploy()方法实际执行
         * */
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/holiday-request.bpmn20.xml")
                .deploy();


        /*
         * 我们现在可以通过API查询验证流程定义已经部署在引擎中（并学习一些API）。
         * 通过RepositoryService创建的ProcessDefinitionQuery对象实现。
         * */
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Who are you?");
        String employee = scanner.nextLine();

        System.out.println("How many holidays do you want to request?");
        Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());

        System.out.println("Why do you need them?");
        String description = scanner.nextLine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        /*
         * 使用RuntimeService启动一个流程实例。收集的数据作为一个java.util.Map实例传递，其中的键就是之后用于获取变量的标识符。这个流程实例使用key启动。
         * 这个key就是BPMN 2.0 XML文件中设置的id属性，在这个例子里是holidayRequest。
         *
         * <process id="holidayRequest" name="Holiday Request" isExecutable="true">
         * */
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", employee);
        variables.put("nrOfHolidays", nrOfHolidays);
        variables.put("description", description);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayRequest", variables);

        /*
         * 要获得实际的任务列表，需要通过TaskService创建一个TaskQuery。
         * 我们配置这个查询只返回’managers’组的任务
         * */
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        System.out.println("You have " + tasks.size() + " tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ") " + tasks.get(i).getName());
        }

        /*
         * 可以使用任务Id获取特定流程实例的变量，并在屏幕上显示实际的申请
         * */
        System.out.println("Which task would you like to complete?");
        int taskIndex = Integer.valueOf(scanner.nextLine());
        Task task = tasks.get(taskIndex - 1);
        Map<String, Object> processVariables = taskService.getVariables(task.getId());
        System.out.println(processVariables.get("employee") + " wants " +
                processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");

        boolean approved = scanner.nextLine().toLowerCase().equals("y");
        variables = new HashMap<String, Object>();
        variables.put("approved", approved);
        taskService.complete(task.getId(), variables);

        /*
         * 如果希望显示流程实例已经执行的时间，就可以从ProcessEngine获取HistoryService，并创建历史活动(historical activities)的查询。
         * 在下面的代码片段中，可以看到我们添加了一些额外的过滤条件：
         *      只选择一个特定流程实例的活动
         *      只选择已完成的活动
         * */
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> activities =
                historyService.createHistoricActivityInstanceQuery()
                        .processInstanceId(processInstance.getId())
                        .finished()
                        .orderByHistoricActivityInstanceEndTime().asc()
                        .list();

        for (HistoricActivityInstance activity : activities) {
            System.out.println(activity.getActivityId() + " took "
                    + activity.getDurationInMillis() + " milliseconds");
        }
    }
}
