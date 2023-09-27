#! https://zhuanlan.zhihu.com/p/656969532
# restful tutorial

## resources 

- tutorial: https://restfulapi.net/rest-architectural-constraints/
- paper pdf: https://d1wqtxts1xzle7.cloudfront.net/43012070/IJEEE-31-40-libre.pdf?1456328318=&response-content-disposition=inline%3B+filename%3DArchitectural_Styles_and_the_Design_of_N.pdf&Expires=1689929329&Signature=Q1kX1lKptqhdJSwkD5lfccfXBz4FfjjL5lYIXxmZgeLA3hsZya6Ht4j6d3IIahH7yYU7hnkJZHsi3tLmsIcGv7TFFAhchtuSGImIQVFvIJD2YSwhZk5YaIN6n9MQE9LH1~pPQxbvZMPz6Ru2cOddbTQCKFO8MLatqTehKWJM~mXHD7805Z6QWPViNJNpCzebRiWJKl5nqpoFFKY0~NEVQVwbZWugIcOAggZMjpnsyY5WwU71VgSd0FB7Oq0vONq6k0h2HYs~cbMX37DorfAPD6hq8UGnhQ8pXDJDzTSyBmGpXop~X4m8VoTWz7OSr4P5CSb~KUOhFIXDyXT2KjRuHw__&Key-Pair-Id=APKAJLOHF5GGSLRBV4ZA
- book: https://www.ics.uci.edu/~fielding/pubs/dissertation/top.htm

## Create REST APIs with JAX-RS

- url: https://restfulapi.net/create-rest-apis-with-jax-rs/

Java EE 6 版本引入了用于 RESTful 网络服务的 Java API (JAX-RS)，从而迈出了 RESTful 网络服务 API 标准化的第一步。JAX-RS 提供了可移植的应用程序接口，用于开发、公开和访问按照 REST 架构风格原则设计和实施的网络应用程序。

JAX-RS 可确保 REST API 代码在所有兼容 Java EE 的应用服务器上的可移植性。最新版本是 JAX-RS 2.0 [JSR 339]，作为 Java EE 7 平台的一部分发布。

### JAX-RS Annotations

让我们来看看 JAX-RS 2.0 提供的一些基本注解。

**@Path('resourcePath')**

`@Path` 注解用于匹配 URI 路径。

```java
@Path("/configurations")
public class ConfigurationResource
{
	@Path("/{id}")
	@GET
	public Response getConfigurationById(@PathParam("id") Integer id) {
		//…
	}
}
```

上例将路径设置为基本 URL + /resourcePath。基础 URL 基于 application name、servlet 和 web.xml 配置文件中的 URL 模式。

**@POST**

@POST 注解方法将在匹配的资源路径上处理 HTTP POST 请求。

```java
@POST
@Consumes("application/xml")
public Response createConfiguration(Configuration config) {
	//...
}
```

**@PUT**

@PUT 注解方法将在匹配的资源路径上处理 HTTP PUT 请求。

```java
@PUT
@Consumes("application/xml")
public Response updateConfiguration(@PathParam("id") Integer id, Configuration config){
	//...
}
```

**@GET**

@GET 注解方法将处理匹配资源路径上的 HTTP GET 请求。

```java
@GET
@Path("/{id}")
public Response getConfigurationById(@PathParam("id") Integer id){
	//...
}
```

**@DELETE**

@DELETE 注解方法将处理匹配资源路径上的 HTTP DELETE 请求。

```java
@DELETE
@Path("/{id}")
public Response deleteConfiguration(@PathParam("id") Integer id){
	//...
}
```

**@PathParam("parameterName")**

@PathParam 用于将 URL 中的值（资源标识符）注入方法参数。

```java
@DELETE
@Path("/{id}")
public Response deleteConfiguration(@PathParam("id") Integer id){
	//...
}
```

在上例中，来自 /{id} 的 id 值将与 @PathParam("id") 整数 id 匹配。例如，URI HTTP DELETE /configurations/22312 将被映射到上述方法，id 将被填充为 22312。

**@Produces**

@Produces 注解定义了被注解的资源方法所传递的 MIME 类型。它既可以在类级别定义，也可以在方法级别定义。

如果在类级别定义，资源类中的所有方法都将返回相同的 MIME 类型，如果没有在任何方法中重载的话。

```java
@Path("/configurations")
@Produces("application/xml")
public class ConfigurationResource {
	//...
}
```

**@Consumes**

@Consumes 注解定义了注解资源方法消耗的 MIME 类型。

```java
@POST
@Consumes("application/xml")
public Response createConfiguration(Configuration config) {
	//...
}
```

**@Context**

为了建立 HATEOAS 链接，JAX-RS 2.0 提供了 UriInfo 类，可以使用 @Context 注解获取该类。

```java
@Context
UriInfo uriInfo;
```

默认情况下，如果没有明确实现，JAX-RS 运行时将自动支持 HEAD 和 OPTIONS 方法。

对于 HEAD，运行时将调用已实现的 GET 方法（如果存在），并忽略响应实体（如果已设置）。

OPTIONS 方法可在 "Allow"（允许）标头中用一组受支持的资源方法返回响应。