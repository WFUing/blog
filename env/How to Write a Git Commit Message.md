#! https://zhuanlan.zhihu.com/p/643857740
# How to Write a Git Commit Message

## Resources

- git tutorial: https://wyag.thb.lt/
- 动图展示10大Git命令: https://zhuanlan.zhihu.com/p/132573100
- git intro: https://missing.csail.mit.edu/2020/version-control/
- book: https://git-scm.com/book/en/v2
- commit convention 规范: https://www.conventionalcommits.org/en/v1.0.0/#summary
- Write yourself a Git：https://wyag.thb.lt/

## 如何编写Git Commit Message? 

为了创建一个有用的 revision history ，团队应该首先就 commit message convention 达成一致，至少要定义以下三点：

- Style：标记语法Markup syntax, 流式布局wrap margins, 语法grammar, 大小写capitalization, 标点符号punctuation。把这些东西写出来，去掉猜测，让一切尽可能简单。
- Content：提交消息的正文应该包含什么样的信息？不应该包含什么？
- Metadata：如何引用 issue tracking IDs、pull request numbers 等？

幸运的是，Git提交信息的规范已经有了很好的约定。事实上，很多 Git 命令的功能中就包含了这些约定。您不需要重新发明什么。只要遵循下面的七条规则，您就能像专家一样 commit message 了。

The seven rules of a great Git commit message

- Separate subject from body with a blank line
- Limit the subject line to 50 characters
- Capitalize the subject line
- Do not end the subject line with a period
- Use the imperative mood in the subject line
- Wrap the body at 72 characters
- Use the body to explain what and why vs. how

For example:

```
Summarize changes in around 50 characters or less

More detailed explanatory text, if necessary. Wrap it to about 72
characters or so. In some contexts, the first line is treated as the
subject of the commit and the rest of the text as the body. The
blank line separating the summary from the body is critical (unless
you omit the body entirely); various tools like `log`, `shortlog`
and `rebase` can get confused if you run the two together.

Explain the problem that this commit is solving. Focus on why you
are making this change as opposed to how (the code explains that).
Are there side effects or other unintuitive consequences of this
change? Here's the place to explain them.

Further paragraphs come after blank lines.

 - Bullet points are okay, too

 - Typically a hyphen or asterisk is used for the bullet, preceded
   by a single space, with blank lines in between, but conventions
   vary here

If you use an issue tracker, put references to them at the bottom,
like this:

Resolves: #123
See also: #456, #789
```

### 1. Separate subject from body with a blank line

From the `git commit` [manpage](https://mirrors.edge.kernel.org/pub/software/scm/git/docs/git-commit.html#_discussion):

```
Though not required, it's a good idea to begin the commit message with a single short (less than 50 character) line summarizing the change, followed by a blank line and then a more thorough description. The text up to the first blank line in a commit message is treated as the commit title, and that title is used throughout Git. For example, Git-format-patch(1) turns a commit into email, and it uses the title on the Subject line and the rest of the commit in the body.
```

首先，并非每次提交都需要主题和正文。有时一行就够了，特别是当修改非常简单，不需要更多上下文的时候。

```
Fix typo in introduction to user guide
```

如果读者想知道错别字是什么，可以直接查 typo 本身，即使用 `git show` 或 `git diff` 或 `git log -p`。

如果您在命令行提交类似的内容，使用 `git commit` 的 `-m` 选项也很方便

```
$ git commit -m "Fix typo in introduction to user guide"
```

然而，当一个提交需要一些解释和上下文时，你需要写一个正文。例如：

```
Derezz the master control program

MCP turned out to be evil and had become intent on world domination.
This commit throws Tron's disc into MCP (causing its deresolution)
and turns it back into a chess game.
```

使用 `-m` 选项编写带正文的提交信息并不容易。最好使用合适的文本编辑器来编写。

在浏览日志时，主体与主体的分离是有好处的。以下是完整的日志记录：

```
$ git log
commit 42e769bdf4894310333942ffc5a15151222a87be
Author: Kevin Flynn <kevin@flynnsarcade.com>
Date:   Fri Jan 01 00:00:00 1982 -0200

 Derezz the master control program

 MCP turned out to be evil and had become intent on world domination.
 This commit throws Tron's disc into MCP (causing its deresolution)
 and turns it back into a chess game.
```

现在只打印主题行 `git log --oneline` ：

```
$ git log --oneline
42e769 Derezz the master control program
```

或者，按用户分组提交，同样只显示主题行，`git shortlog`：

```
$ git shortlog
Kevin Flynn (1):
      Derezz the master control program

Alan Bradley (1):
      Introduce security program "Tron"

Ed Dillinger (3):
      Rename chess program to "MCP"
      Modify chess program
      Upgrade chess program

Walter Gibbs (1):
      Introduce protoype chess program
```

在Git中，主题行和正文之间的区别还有很多，但如果中间没有空行，它们都无法正常工作。

### 2. Limit the subject line to 50 characters

50个字符不是硬性限制，只是一个经验法则。将主题行保持在这一长度可确保其可读性，并迫使作者思考如何以最简洁的方式说明内容。

```
Tip: If you're having a hard time summarizing, you might be committing too many changes at once. Strive for atomic commits (a topic for a separate post).
```

GitHub's UI is fully aware of these conventions. It will warn you if you go past the 50 character limit and will truncate any subject line longer than 72 characters with an ellipsis. 

### 3. Capitalize the subject line

This is as simple as it sounds. Begin all subject lines with a capital letter.

For example:

- Accelerate to 88 miles per hour

Instead of:

- ~~accelerate to 88 miles per hour~~

### 4. Do not end the subject line with a period

Trailing punctuation is unnecessary in subject lines. Besides, space is precious when you're trying to keep them to 50 chars or less.

Example:

- Open the pod bay doors

Instead of:

- ~~Open the pod bay doors.~~

### 5. Use the imperative mood in the subject line

Imperative mood just means "spoken or written as if giving a command or instruction". A few examples:

- Clean your room
- Close the door
- Take out the trash

Git itself uses the imperative whenever it creates a commit on your behalf.

例如，使用 git merge 时创建的默认信息如下

```
Merge branch 'myfeature'
```

当使用 `git revert` 时，

```
Revert "Add the thing with the stuff"

This reverts commit cc87791524aedd593cff5a74532befe7ab69ce9d.
```

或 点击 GitHub 拉取请求上的 `Merge` 按钮时：

```
Merge pull request #123 from someuser/somebranch
```

因此，当您在命令行中编写提交信息时，您遵循的是 Git 自带的约定。例如，

- Refactor subsystem X for readability
- Update getting started documentation
- Remove deprecated methods
- Release version 1.0.0

这样写一开始可能会有点尴尬。我们更习惯于用指示语气说话，而指示语气则是报告事实。这就是为什么提交的信息经常读起来像这样：

- ~~Fixed bug with Y~~
- ~~Changing behavior of X~~

有时承诺信息会被写成内容描述：

- ~~More fixes for broken stuff~~
- ~~Sweet new API methods~~

为了消除任何混淆，这里有一个简单的规则，以便每次都能正确操作。

一个正确的Git提交主题行应该能够完成以下句子：

- If applied, this commit will your subject line here

For example:

- If applied, this commit will refactor subsystem X for readability
- If applied, this commit will update getting started documentation
- If applied, this commit will remove deprecated methods
- If applied, this commit will release version 1.0.0
- If applied, this commit will merge pull request #123 from user/branch

Remember: Use of the imperative is important only in the subject line. You can relax this restriction when you're writing the body.

### 6. Wrap the body at 72 characters

Git 不会自动换行。当您写提交信息的正文时，必须注意右边距，并手动换行。

建议在72个字符时进行，这样Git就有足够的空间缩进文本，同时又能将所有内容保持在80个字符以内。

### 7. Use the body to explain what and why vs. how

Bitcoin Core 的这个 commit 是一个很好的例子，它解释了改变的内容和原因：

```
commit eb0b56b19017ab5c16c745e6da39c53126924ed6
Author: Pieter Wuille <pieter.wuille@gmail.com>
Date:   Fri Aug 1 22:57:55 2014 +0200

   Simplify serialize.h's exception handling

   Remove the 'state' and 'exceptmask' from serialize.h's stream
   implementations, as well as related methods.

   As exceptmask always included 'failbit', and setstate was always
   called with bits = failbit, all it did was immediately raise an
   exception. Get rid of those variables, and replace the setstate
   with direct exception throwing (which also removes some dead
   code).

   As a result, good() is never reached after a failure (there are
   only 2 calls, one of which is in tests), and can just be replaced
   by !eof().

   fail(), clear(n) and exceptions() are just never called. Delete
   them.
```

看看完整的差异，想想作者花时间在此时此地提供这些上下文，为同事和未来的提交者节省了多少时间。如果他不这样做，这些内容可能会永远丢失。

在大多数情况下，您可以省略关于如何修改的细节。在这方面，代码通常是不言自明的，如果代码非常复杂，需要用散文来解释，那就是源注释的作用。只需重点说明您首先进行修改的原因--(修改前的工作方式以及有什么问题)、现在的工作方式，以及您为什么决定以这种方式解决问题。

未来感谢您的维护者可能就是您自己！

