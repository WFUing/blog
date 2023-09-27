#! https://zhuanlan.zhihu.com/p/657596908
# Actor Model

CPU 上有多个内核。如果我们想充分利用现有的这些硬件，就需要一种并发运行代码的方法。数十年来无法追踪的错误和开发人员的沮丧都表明，线程并不是解决问题的办法。不过不用担心，我们还有其他很好的选择，今天我要向你展示的就是其中之一：actor model。

## actor model

actor model 是一种处理并发计算的概念模型。它为系统组件的行为和交互方式定义了一些通用规则。

### actors

actor 是计算的原始单元。它接收 message，并根据 message进行某种计算。

这种想法与面向对象语言（object-oriented languages）中的想法非常相似：对象接收 message（方法调用），并根据接收到的 message（我们调用的方法）进行操作。

主要区别在于，actors 之间是完全隔离的，它们永远不会共享内存。值得注意的是，一个 actor 可以保持一个私有状态，其他 actor 永远无法直接改变该状态。

一个 actor 不是 actor。它们是以系统的形式出现的。在 actor model 中，一切都是 actor，它们需要有地址，这样一个行为者才能向另一个 actor 发送 message。

### mailbox

虽然多个 actor 可以同时运行，但一个 actor 会按顺序处理给定的 message。这意味着，如果你向同一个 actor 发送 3 条 message，它只会一次执行一条。要同时执行这 3 条 message，你需要创建 3 个 actor，每个 actor 发送一条 message。

message 是异步发送给角色的，角色在处理另一条消息时需要将消息存储在某个地方。mailbox 就是存储这些 message 的地方。

![mailbox](static/actor/mailbox.png)

actor 之间通过发送异步消息进行通信。这些 message 会保存在其他 actor 的 mailbox 中，直到它们被处理。

## What actors do

当 actor 收到 message 时，它可以做以下三件事中的一件：

- Create more actors
- Send messages to other actors
- Designate what to do with the next message：指定义这个状态在收到下一条信息时的样子，行为体如何改变状态。假设我们有一个行为类似于计算器的行为体，它的初始状态是简单的数字 0。当这个行为体收到 add(1) 消息时，它不会改变自己的原始状态，而是指定在收到下一条消息时，状态将是 1。

## Fault tolerance

Erlang 引入了 "let it crash" 的理念。其理念是，你不需要进行防御性编程，试图预测所有可能发生的问题，并找到处理它们的方法，因为根本不可能考虑到每一个故障点。

Erlang 所做的就是简单地让它崩溃，但让这些关键代码由某个人监管，而这个人唯一的责任就是知道当崩溃发生时该做什么（比如将代码单元重置为稳定状态），而使这一切成为可能的就是 actor model。

每段代码都运行在一个进程中（这也是 Erlang 对其角色的基本称呼）。这个进程是完全孤立的，这意味着它的状态不会影响任何其他进程。我们有一个 "监督者"，它基本上是另一个进程（所有东西都是行为体，还记得吗？），当被监督的进程崩溃时，它会收到通知，然后可以采取一些措施。

这就使得创建 "self heal" 系统成为可能，也就是说，如果一个行为体由于某种原因进入了异常状态并崩溃，那么监管者就可以采取一些措施，尝试将其恢复到一致的状态（有多种策略可以做到这一点，最常见的就是以初始状态重新启动行为体）。

