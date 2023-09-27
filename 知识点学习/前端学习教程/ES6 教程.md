# ES6 教程

ES6， 全称 ECMAScript 6.0 ，是 JavaScript 的下一个版本标准，2015.06 发版。

ES6 主要是为了解决 ES5 的先天不足，比如 JavaScript 里并没有类的概念，但是目前浏览器的 JavaScript 是 ES5 版本，大多数高版本的浏览器也支持 ES6，不过只实现了 ES6 的部分特性和功能。

## 1 ES6 环境搭建 

目前各大浏览器基本上都支持 ES6 的新特性，其中 Chrome 和 Firefox 浏览器对 ES6 新特性最友好，IE7~11 基本不支持 ES6。

1. 桌面端浏览器对ES2015的支持情况
	-  Chrome：51 版起便可以支持 97% 的 ES6 新特性。
	-  Firefox：53 版起便可以支持 97% 的 ES6 新特性。
	-  Safari：10 版起便可以支持 99% 的 ES6 新特性。
	-  IE：Edge 15可以支持 96% 的 ES6 新特性。Edge 14 可以支持 93% 的 ES6 新特性。（IE7~11 基本不支持 ES6）
2. 移动端浏览器对ES2015的支持情况
	-  iOS：10.0 版起便可以支持 99% 的 ES6 新特性。
	-  Android：基本不支持 ES6 新特性（5.1 仅支持 25%）
3. 服务器对ES2015的支持情况
	-  Node.js：6.5 版起便可以支持 97% 的 ES6 新特性。（6.0 支持 92%）
4. 附：如何使用ES6的新特性，又能保证浏览器的兼容？
	- 针对 ES6 的兼容性问题，很多团队为此开发出了多种语法解析转换工具，把我们写的 ES6 语法转换成 ES5，相当于在 ES6 和浏览器之间做了一个翻译官。比较通用的工具方案有 babel，jsx，traceur，es6-shim 等。

**实例**

```javascript
var a = 2;
{
  let a = 3;
  document.write(a); // 3
}
document.write('<br>');
document.write(a); // 2
```

### 1.1 在 Node.js 环境中运行 ES6

```
$ node
> let sitename="runoob"
undefined
> console.log(sitename)
runoob
undefined
>
```

使用下面的命令，可以查看 Node 已经实现的 ES6 特性。

```
node --v8-options | grep harmony
```

### 1.2 webpack

[webpack](https://www.runoob.com/w3cnote/webpack-tutorial.html) 是一个现代 JavaScript 应用程序的静态模块打包器 (module bundler) 。当 webpack 处理应用程序时，它会递归地构建一个依赖关系图 (dependency graph) ，其中包含应用程序需要的每个模块，然后将所有这些模块打包成一个或多个 bundle 。

webpack 主要有四个核心概念：

- 入口 (entry)
- 输出 (output)
- loader
- 插件 (plugins)

#### 1.2.1 入口 (entry)

入口会指示 webpack 应该使用哪个模块，来作为构建其内部依赖图的开始。进入入口起点后，webpack 会找出有哪些模块和库是入口起点（直接和间接）依赖的。在 webpack 中入口有多种方式来定义，如下面例子：

**单个入口（简写）语法**：

```javascript
const config = {
  entry: "./src/main.js"
}
```

**对象语法**：

```javascript
const config = {
  app: "./src/main.js",
  vendors: "./src/vendors.js"
}
```

#### 1.2.2 输出 (output)

output 属性会告诉 webpack 在哪里输出它创建的 bundles ，以及如何命名这些文件，默认值为 ./dist:

```javascript
const config = {
  entry: "./src/main.js",
  output: {
    filename: "bundle.js",
    path: path.resolve(__dirname, 'dist')
  }
}
```

#### 1.2.3 loader

loader 让 webpack 可以去处理那些非 JavaScript 文件（ webpack 自身只理解 JavaScript ）。loader 可以将所有类型的文件转换为 webpack 能够有效处理的模块，例如，开发的时候使用 ES6 ，通过 loader 将 ES6 的语法转为 ES5 ，如下配置：

```javascript
const config = {
  entry: "./src/main.js",
  output: {
    filename: "bundle.js",
    path: path.resolve(__dirname, 'dist')
  },
  module: {
    rules: [
      {
          test: /\.js$/,
          exclude: /node_modules/,
          loader: "babel-loader",
          options: [
            presets: ["env"]
          ]
      }
    ]
  }
}
```

#### 1.2.4 插件 (plugins)

loader 被用于转换某些类型的模块，而插件则可以做更多的事情。包括打包优化、压缩、定义环境变量等等。插件的功能强大，是 webpack 扩展非常重要的利器，可以用来处理各种各样的任务。使用一个插件也非常容易，只需要 require() ，然后添加到 plugins 数组中。

```javascript
// 通过 npm 安装
const HtmlWebpackPlugin = require('html-webpack-plugin');
// 用于访问内置插件 
const webpack = require('webpack'); 
 
const config = {
  module: {
    rules: [
      {
          test: /\.js$/,
          exclude: /node_modules/,
          loader: "babel-loader"
      }
    ]
  },
  plugins: [
    new HtmlWebpackPlugin({template: './src/index.html'})
  ]
};
```

#### 1.2.5 利用 webpack 搭建应用

```javascript
// webpack.config.js
const path = require('path');
 
module.exports = {
  mode: "development", // "production" | "development"
  // 选择 development 为开发模式， production 为生产模式
  entry: "./src/main.js",
  output: {
    filename: "bundle.js",
    path: path.resolve(__dirname, 'dist')
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        loader: "babel-loader",
        options: [
          presets: ["env"]
        ]
      }
    ]
  },
  plugins: [
    ...
  ]
}
```

上述例子构建了一个最简单的配置，webpack 会从入口 main.js 文件进行构建，通过 loader 进行js转换，输出一个为 bundle.js 的文件，至此一整个过程就构建完成。

### 1.3 gulp

gulp 是一个基于流的自动化构建工具，具有易于使用、构建快速、插件高质和易于学习的特点，常用于轻量级的工程中。

#### 1.3.1 如何使用？

全局安装 gulp:

```
$ npm install --global gulp
```

在项目中引入依赖:

```
$ npm install --save-dev gulp
```

在项目根目录下创建名为 gulpfile.js 的文件:

```javascript
const gulp = require('gulp');

// default 表示一个任务名，为默认执行任务
gulp.task('default', function() {
  // 放置默认的任务代码
})
```

运行 gulp:

```
$ gulp
```

## 2. ES6 声明与表达式

### 2.1 let 与 const

ES2015(ES6) 新增加了两个重要的 JavaScript 关键字：**let** 和 **const**。

- **let** 声明的变量只在 let 命令所在的代码块内有效。
- **const** 声明一个只读的常量，一旦声明，常量的值就不能改变。

#### 2.1.1 let 命令

##### 2.1.1.1 let 基本用法

```javascript
{
  let a = 0;
  a   // 0
}
a   // 报错 ReferenceError: a is not defined
```

##### 2.1.1.2 let 在代码块内有效

let 是在代码块内有效，var 是在全局范围内有效。

```javascript
{
  let a = 0;
  var b = 1;
}
a  // ReferenceError: a is not defined
b  // 1
```

##### 2.1.1.3 let 不能重复声明

let 只能声明一次 var 可以声明多次。

```javascript
let a = 1;
let a = 2;
var b = 3;
var b = 4;
a  // Identifier 'a' has already been declared
b  // 4
```

##### 2.1.1.4 let 适合用作 for 循环计数器

**for 循环计数器很适合用 let**。

```javascript
for (var i = 0; i < 10; i++) {
  setTimeout(function(){
    console.log(i);
  })
}
// 输出十个 10
for (let j = 0; j < 10; j++) {
  setTimeout(function(){
    console.log(j);
  })
}
// 输出 0123456789
```

- 变量 i 是用 var 声明的，在全局范围内有效，所以全局中只有一个变量 i， 每次循环时，setTimeout 定时器里面的 i 指的是全局变量 i ，而循环里的十个 setTimeout 是在循环结束后才执行，所以此时的 i 都是 10。
- 变量 j 是用 let 声明的，当前的 j 只在本轮循环中有效，每次循环的 j 其实都是一个新的变量，所以 setTimeout 定时器里面的 j 其实是不同的变量，即最后输出 12345。（若每次循环的变量 j 都是重新声明的，如何知道前一个循环的值？这是因为 JavaScript 引擎内部会记住前一个循环的值）。

##### 2.1.1.5 let 不存在变量提升

let 不存在变量提升，var 会变量提升。

```javascript
console.log(a);  //ReferenceError: a is not defined
let a = "apple";
 
console.log(b);  //undefined
var b = "banana";
```

- 变量 b 用 var 声明存在变量提升，所以当脚本开始运行的时候，b 已经存在了，但是还没有赋值，所以会输出 undefined。
- 变量 a 用 let 声明不存在变量提升，在声明变量 a 之前，a 不存在，所以会报错。

#### 2.1.2 const 命令

const 声明一个只读变量，声明之后不允许改变。意味着，一旦声明必须初始化，否则会报错。

##### 2.1.2.1 const 基本用法

```javascript
const PI = "3.1415926";
PI  // 3.1415926

const MY_AGE;  // SyntaxError: Missing initializer in const declaration
```

##### 2.1.2.2 const 暂时性死区

```javascript
var PI = "a";
if(true){
  console.log(PI);  // Cannot access 'PI' before initialization
  const PI = "3.1415926";
}
```

ES6 明确规定，代码块内如果存在 let 或者 const，代码块会对这些命令声明的变量从块的开始就形成一个封闭作用域。代码块内，在声明变量 PI 之前使用它会报错。

##### 2.1.2.3 const 如何做到变量在声明初始化之后不允许改变的？

其实 const 其实保证的不是变量的值不变，而是保证变量指向的内存地址所保存的数据不允许改动。此时，你可能已经想到，简单类型和复合类型保存值的方式是不同的。是的，对于简单类型（数值 number、字符串 string 、布尔值 boolean），值就保存在变量指向的那个内存地址，因此 const 声明的简单类型变量等同于常量。而复杂类型（对象 object，数组 array，函数 function），变量指向的内存地址其实是保存了一个指向实际数据的指针，所以 const 只能保证指针是固定的，至于指针指向的数据结构变不变就无法控制了，所以使用 const 声明复杂类型对象时要慎重。

### 2.2 解构赋值

解构赋值是对赋值运算符的扩展。

他是一种针对数组或者对象进行模式匹配，然后对其中的变量进行赋值。

在代码书写上简洁且易读，语义更加清晰明了；也方便了复杂对象中数据字段获取。

#### 2.2.1 解构模型

在解构中，有下面两部分参与：

- 解构的源，解构赋值表达式的右边部分。
- 解构的目标，解构赋值表达式的左边部分。

#### 2.2.2 数组模型的解构（Array）

##### 2.2.2.1 基本

```javascript
let [a, b, c] = [1, 2, 3];
// a = 1
// b = 2
// c = 3
```

##### 2.2.2.2 可嵌套

```javascript
let [a, [[b], c]] = [1, [[2], 3]];
// a = 1
// b = 2
// c = 3
```

##### 2.2.2.3 可忽略

```javascript
let [a, , b] = [1, 2, 3];
// a = 1
// b = 3
```

##### 2.2.2.4 不完全解构

```javascript
let [a = 1, b] = []; // a = 1, b = undefined
```

##### 2.2.2.5 剩余运算符

```javascript
let [a, ...b] = [1, 2, 3];
//a = 1
//b = [2, 3]
```

##### 2.2.2.6 字符串等

在数组的解构中，解构的目标若为可遍历对象，皆可进行解构赋值。可遍历对象即实现 Iterator 接口的数据。

```javascript
let [a, b, c, d, e] = 'hello';
// a = 'h'
// b = 'e'
// c = 'l'
// d = 'l'
// e = 'o'
```

##### 2.2.2.7 解构默认值

```javascript
let [a = 2] = [undefined]; // a = 2
```

当解构模式有匹配结果，且匹配结果是 undefined 时，会触发默认值作为返回结果。

```javascript
let [a = 3, b = a] = [];     // a = 3, b = 3
let [a = 3, b = a] = [1];    // a = 1, b = 1
let [a = 3, b = a] = [1, 2]; // a = 1, b = 2
```

- a 与 b 匹配结果为 undefined ，触发默认值：**a = 3; b = a =3**
- a 正常解构赋值，匹配结果：a = 1，b 匹配结果 undefined ，触发默认值：**b = a =1**
- a 与 b 正常解构赋值，匹配结果：**a = 1，b = 2**

#### 2.2.3 对象模型的解构（Object）

##### 2.2.3.1 基本

```javascript
let { foo, bar } = { foo: 'aaa', bar: 'bbb' };
// foo = 'aaa'
// bar = 'bbb'
 
let { baz : foo } = { baz : 'ddd' };
// foo = 'ddd'
```

##### 2.2.3.2 可嵌套可忽略

```javascript
let obj = {p: ['hello', {y: 'world'}] };
let {p: [x, { y }] } = obj;
// x = 'hello'
// y = 'world'
let obj = {p: ['hello', {y: 'world'}] };
let {p: [x, {  }] } = obj;
// x = 'hello'
```

##### 2.2.3.3 不完全解构

```javascript
let obj = {p: [{y: 'world'}] };
let {p: [{ y }, x ] } = obj;
// x = undefined
// y = 'world'
```

##### 2.2.3.4 剩余运算符

```javascript
let {a, b, ...rest} = {a: 10, b: 20, c: 30, d: 40};
// a = 10
// b = 20
// rest = {c: 30, d: 40}
```

##### 2.2.3.5 解构默认值

```javascript
let {a = 10, b = 5} = {a: 3};
// a = 3; b = 5;
let {a: aa = 10, b: bb = 5} = {a: 3};
// aa = 3; bb = 5;
```

### 2.3 Symbol

Symbol是 [ES6](https://so.csdn.net/so/search?q=ES6&spm=1001.2101.3001.7020) 中引入的一种新的基本数据类型，用于表示一个独一无二的值。它是JavaScript中的第七种数据类型，与undefined、null、Number（数值）、String（字符串）、Boolean（布尔值）、Object（对象）并列。

#### 2.3.1 基本用法

Symbol 函数栈不能用 new 命令，因为 Symbol 是原始数据类型，不是对象。可以接受一个字符串作为参数，为新创建的 Symbol 提供描述，用来显示在控制台或者作为字符串的时候使用，便于区分。

```javascript
const a = Symbol();
console.log(a);  //Symbol()

//报错，Symbol is not a constructor
const a = new Symbol();
```

使用Symbol()创建一个Symbol类型的值并赋值给a变量后，你就得到了一个在内存中独一无二的值。现在除了通过变量a，任何人在任何作用域内都无法重新创建出这个值。例如当你这样写：

```javascript
const b = Symbol();
```

尽管a和b都是使用 Symbol() 创建出来的，但是它们在内存中看起来却是这样的：

![img](ES6%20%E6%95%99%E7%A8%8B.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNjk0Mjkx,size_16,color_FFFFFF,t_70-16660101175852.png)

实际上，a变量拿到了内存中某块内存的唯一引用（这里所说的引用，其实就是该内存的地址）。如果不借助a变量，你不可能再得到这个地址。因此：

```javascript
a !== b;  //a和b持有的是两块内存的引用
 
const c = a;  //手动把a里保存的地址保存在c变量中
a === c;  //c和a现在指向同一块内存，因为它们保存了同样的地址
```

![img](ES6%20%E6%95%99%E7%A8%8B.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNjk0Mjkx,size_16,color_FFFFFF,t_70-16660101682273-16660101691595.png)

增加一个例子，以加强理解：

```javascript
let sy = Symbol("KK");
console.log(sy);   // Symbol(KK)
typeof(sy);        // "symbol"
 
// 相同参数 Symbol() 返回的值不相等
let sy1 = Symbol("kk"); 
sy === sy1;       // false
```

这种行为看似难以理解，但其实它与对象遵循相同的规则，如：

```javascript
var a = {};
var b = {};
 
a !== b;  //a和b各自被分配了不同的内存，因此它们保存了不同的地址
 
//借助变量a，变量c拿到了a指向的那个对象的地址，因此两者相等
var c = a;
a === c;
```

但是对于同为基本数据类型的[字符串](https://so.csdn.net/so/search?q=字符串&spm=1001.2101.3001.7020)来说，它不遵循类似的规则。比如：

```javascript
var a = "123";
var b = "123";
 
a === b;  //返回true。两者在常量区引用同一个字符串
```

![img](ES6%20%E6%95%99%E7%A8%8B.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxNjk0Mjkx,size_16,color_FFFFFF,t_70-16660102822406-16660102830688.png)

我们首先通过变量a在内存中创建了字符串“123”，然后在不借助变量a的情况下，又通过var b = "123"拿到了对“123”这个字符串的引用，两者指向内存中的同一块内存地址。

因此我们说，a无法确保别的变量无法拿到它保存的地址（前提是不通过a）。但是对于var a = Symbol()这样的语句，a变量内保存的值是唯一的，因为除了借助a变量，你永远无法得到a中保存的值。这也是Symbol的本质。

可能很多人比较奇怪，一个Symbol类型的变量里面到底保存了什么呢？

我们看两行代码：

```javascript
var a = Symbol();
 
console.log(a);  //Symbol()
```

我们试图输出a的值，但js引擎输出了Symbol()。显然它不能说明a的值是字符串，因为：

```javascript
typeof a === "symbol";
```

所以说如果你想问js引擎a的值是多少，引擎只会告诉你它是一个Symbol类型的值。也就是说，Symbol真正存储了什么并不重要，重要的是它的值永远不会与别的值相等。Symbol的中文释义为“标志，符号”，一个Symbol类型的变量只是为了标记一块唯一的内存而存在的。也正是因为这样，Symbol类型的值不参与运算。

#### 2.3.2 使用场景

##### 2.3.2.1 作为属性名

由于每一个 Symbol 的值都是不相等的，所以 Symbol 作为对象的属性名，可以保证属性不重名。

```javascript
let sy = Symbol("key1");
 
// 写法1
let syObject = {};
syObject[sy] = "kk";
console.log(syObject);    // {Symbol(key1): "kk"}
 
// 写法2
let syObject = {
  [sy]: "kk"
};
console.log(syObject);    // {Symbol(key1): "kk"}
 
// 写法3
let syObject = {};
Object.defineProperty(syObject, sy, {value: "kk"});
console.log(syObject);   // {Symbol(key1): "kk"}
```

Symbol 作为对象属性名时不能用.运算符，要用方括号。因为.运算符后面是字符串，所以取到的是字符串 sy 属性，而不是 Symbol 值 sy 属性。

```javascript
let syObject = {};
syObject[sy] = "kk";
 
syObject[sy];  // "kk"
syObject.sy;   // undefined
```

##### 2.3.2.2 注意点

Symbol 值作为属性名时，该属性是公有属性不是私有属性，可以在类的外部访问。但是不会出现在 for...in 、 for...of 的循环中，也不会被 Object.keys() 、 Object.getOwnPropertyNames() 返回。如果要读取到一个对象的 Symbol 属性，可以通过 Object.getOwnPropertySymbols() 和 Reflect.ownKeys() 取到。

```javascript
let syObject = {};
syObject[sy] = "kk";
console.log(syObject);
 
for (let i in syObject) {
  console.log(i);
}    // 无输出
 
Object.keys(syObject);                     // []
Object.getOwnPropertySymbols(syObject);    // [Symbol(key1)]
Reflect.ownKeys(syObject);                 // [Symbol(key1)]
```

##### 2.3.2.3 定义常量

在 ES5 使用字符串表示常量。例如：

```javascript
const COLOR_RED = "red";
const COLOR_YELLOW = "yellow";
const COLOR_BLUE = "blue";
```

但是用字符串不能保证常量是独特的，这样会引起一些问题：

```javascript
const COLOR_RED = "red";
const COLOR_YELLOW = "yellow";
const COLOR_BLUE = "blue";
const MY_BLUE = "blue";
 
function ColorException(message) {
   this.message = message;
   this.name = "ColorException";
}
 
function getConstantName(color) {
    switch (color) {
        case COLOR_RED :
            return "COLOR_RED";
        case COLOR_YELLOW :
            return "COLOR_YELLOW ";
        case COLOR_BLUE:
            return "COLOR_BLUE";
        case MY_BLUE:
            return "MY_BLUE";         
        default:
            throw new ColorException("Can't find this color");
    }
}
 
try {
   var color = "green"; // green 引发异常
   var colorName = getConstantName(color);
} catch (e) {
   var colorName = "unknown";
   console.log(e.message, e.name); // 传递异常对象到错误处理
}
```

但是使用 Symbol 定义常量，这样就可以保证这一组常量的值都不相等。用 Symbol 来修改上面的例子。

```javascript
const COLOR_RED = Symbol("red");
const COLOR_YELLOW = Symbol("yellow");
const COLOR_BLUE = Symbol("blue");
 
function ColorException(message) {
   this.message = message;
   this.name = "ColorException";
}
function getConstantName(color) {
    switch (color) {
        case COLOR_RED :
            return "COLOR_RED";
        case COLOR_YELLOW :
            return "COLOR_YELLOW ";
        case COLOR_BLUE:
            return "COLOR_BLUE";
        default:
            throw new ColorException("Can't find this color");
    }
}
 
try {
   var color = "green"; // green 引发异常
   var colorName = getConstantName(color);
} catch (e) {
   var colorName = "unknown";
   console.log(e.message, e.name); // 传递异常对象到错误处理
}
```

Symbol 的值是唯一的，所以不会出现相同值得常量，即可以保证 switch 按照代码预想的方式执行。

##### 2.3.2.4 Symbol.for()

Symbol.for() 类似单例模式，首先会在全局搜索被登记的 Symbol 中是否有该字符串参数作为名称的 Symbol 值，如果有即返回该 Symbol 值，若没有则新建并返回一个以该字符串参数为名称的 Symbol 值，并登记在全局环境中供搜索。

```javascript
let yellow = Symbol("Yellow");
let yellow1 = Symbol.for("Yellow");
yellow === yellow1;      // false
 
let yellow2 = Symbol.for("Yellow");
yellow1 === yellow2;     // true
```

##### 2.3.2.5 Symbol.keyFor()

Symbol.keyFor() 返回一个已登记的 Symbol 类型值的 key ，用来检测该字符串参数作为名称的 Symbol 值是否已被登记。

```javascript
let yellow1 = Symbol.for("Yellow");
Symbol.keyFor(yellow1);    // "Yellow"
```

## 3. 内置对象

### 3.1 新增

#### 3.1.1 Map 与 Set

##### 3.1.1.1 Map 对象

Map 对象保存键值对。任何值(对象或者原始值) 都可以作为一个键或一个值。

######  3.1.1.1.1 Maps 和 Objects 的区别

- 一个 Object 的键只能是字符串或者 Symbols，但一个 Map 的键可以是任意值。
- Map 中的键值是有序的（FIFO 原则），而添加到对象中的键则不是。
- Map 的键值对个数可以从 size 属性获取，而 Object 的键值对个数只能手动计算。
- Object 都有自己的原型，原型链上的键名有可能和你自己在对象上的设置的键名产生冲突。
- ![img](ES6%20%E6%95%99%E7%A8%8B.assets/1_HmXTMmVps1oJ7MU-odCpUA-166601062606410.jpeg)

###### 3.1.1.1.2 Map 中的 key

**key 是字符串**

````javascript
var myMap = new Map();
var keyString = "a string"; 
 
myMap.set(keyString, "和键'a string'关联的值");
 
myMap.get(keyString);    // "和键'a string'关联的值"
myMap.get("a string");   // "和键'a string'关联的值"
                         // 因为 keyString === 'a string'
````

**key 是对象**

```javascript
var myMap = new Map();
var keyObj = {}, 
 
myMap.set(keyObj, "和键 keyObj 关联的值");

myMap.get(keyObj); // "和键 keyObj 关联的值"
myMap.get({}); // undefined, 因为 keyObj !== {}
```

**key 是函数**

```javascript
var myMap = new Map();
var keyFunc = function () {}, // 函数
 
myMap.set(keyFunc, "和键 keyFunc 关联的值");
 
myMap.get(keyFunc); // "和键 keyFunc 关联的值"
myMap.get(function() {}) // undefined, 因为 keyFunc !== function () {}
```

**key 是 NaN**

```javascript
var myMap = new Map();
myMap.set(NaN, "not a number");
 
myMap.get(NaN); // "not a number"
 
var otherNaN = Number("foo");
myMap.get(otherNaN); // "not a number"
```

虽然 NaN 和任何值甚至和自己都不相等(NaN !== NaN 返回true)，NaN作为Map的键来说是没有区别的。

###### 3.1.1.1.3 Map 的迭代

对 Map 进行遍历，以下两个最高级。

**for...of**

```javascript
var myMap = new Map();
myMap.set(0, "zero");
myMap.set(1, "one");
 
// 将会显示两个 log。 一个是 "0 = zero" 另一个是 "1 = one"
for (var [key, value] of myMap) {
  console.log(key + " = " + value);
}
for (var [key, value] of myMap.entries()) {
  console.log(key + " = " + value);
}
/* 这个 entries 方法返回一个新的 Iterator 对象，它按插入顺序包含了 Map 对象中每个元素的 [key, value] 数组。 */
 
// 将会显示两个log。 一个是 "0" 另一个是 "1"
for (var key of myMap.keys()) {
  console.log(key);
}
/* 这个 keys 方法返回一个新的 Iterator 对象， 它按插入顺序包含了 Map 对象中每个元素的键。 */
 
// 将会显示两个log。 一个是 "zero" 另一个是 "one"
for (var value of myMap.values()) {
  console.log(value);
}
/* 这个 values 方法返回一个新的 Iterator 对象，它按插入顺序包含了 Map 对象中每个元素的值。 */
```

**forEach()**

```javascript
var myMap = new Map();
myMap.set(0, "zero");
myMap.set(1, "one");
 
// 将会显示两个 logs。 一个是 "0 = zero" 另一个是 "1 = one"
myMap.forEach(function(value, key) {
  console.log(key + " = " + value);
}, myMap)
```

###### 3.1.1.1.4 Map 对象的操作

**Map 与 Array的转换**

```javascript
var kvArray = [["key1", "value1"], ["key2", "value2"]];
 
// Map 构造函数可以将一个 二维 键值对数组转换成一个 Map 对象
var myMap = new Map(kvArray);
 
// 使用 Array.from 函数可以将一个 Map 对象转换成一个二维键值对数组
var outArray = Array.from(myMap);
```

**Map 的克隆**

```javascript
var myMap1 = new Map([["key1", "value1"], ["key2", "value2"]]);
var myMap2 = new Map(myMap1);
 
console.log(original === clone); 
// 打印 false。 Map 对象构造函数生成实例，迭代出新的对象。
```

**Map 的合并**

```javascript
var first = new Map([[1, 'one'], [2, 'two'], [3, 'three'],]);
var second = new Map([[1, 'uno'], [2, 'dos']]);
 
// 合并两个 Map 对象时，如果有重复的键值，则后面的会覆盖前面的，对应值即 uno，dos， three
var merged = new Map([...first, ...second]);
```

##### 3.1.1.2 Set 对象

Set 对象允许你存储任何类型的唯一值，无论是原始值或者是对象引用。

###### 3.1.1.2.1 Set 中的特殊值

Set 对象存储的值总是唯一的，所以需要判断两个值是否恒等。有几个特殊值需要特殊对待：

- +0 与 -0 在存储判断唯一性的时候是恒等的，所以不重复；
- undefined 与 undefined 是恒等的，所以不重复；
- NaN 与 NaN 是不恒等的，但是在 Set 中只能存一个，不重复。

```javascript
let mySet = new Set();
 
mySet.add(1); // Set(1) {1}
mySet.add(5); // Set(2) {1, 5}
mySet.add(5); // Set(2) {1, 5} 这里体现了值的唯一性
mySet.add("some text"); 
// Set(3) {1, 5, "some text"} 这里体现了类型的多样性
var o = {a: 1, b: 2}; 
mySet.add(o);
mySet.add({a: 1, b: 2}); 
// Set(5) {1, 5, "some text", {…}, {…}} 
// 这里体现了对象之间引用不同不恒等，即使值相同，Set 也能存储
```

###### 3.1.1.2.2 类型转换

**Array**

```javascript
// Array 转 Set
var mySet = new Set(["value1", "value2", "value3"]);
// 用...操作符，将 Set 转 Array
var myArray = [...mySet];

// String 转 Set
var mySet = new Set('hello');  // Set(4) {"h", "e", "l", "o"}
// 注：Set 中 toString 方法是不能将 Set 转换成 String
```

###### 3.1.1.2.3 Set 对象作用

**数组去重**

```javascript
var mySet = new Set([1, 2, 3, 4, 4]);
[...mySet]; // [1, 2, 3, 4]
```

**并集**

```javascript
var a = new Set([1, 2, 3]);
var b = new Set([4, 3, 2]);
var union = new Set([...a, ...b]); // {1, 2, 3, 4}
```

**交集**

```javascript
var a = new Set([1, 2, 3]);
var b = new Set([4, 3, 2]);
var intersect = new Set([...a].filter(x => b.has(x))); // {2, 3}
```

**差集**

```javascript
var a = new Set([1, 2, 3]);
var b = new Set([4, 3, 2]);
var difference = new Set([...a].filter(x => !b.has(x))); // {1}

var a = new Set([1, 2, 3]);
var b = new Set([4, 3, 2]);
var difference =new Set([...[...a].filter(x => !b.has(x)),...[...b].filter(x => !a.has(x))]); // {1,4}
// 1 为 a 与 b 的差集。
// 4 为 b 与 a 的差集。
```

**1、[...a]**

[...a] 就是将 set 转换成 array。以后需要将 set 转换成 array 基本都使用这种方法。

**2、[...a].filter()**

Array.filter(function(x)) 把传入的函数 function(x) 依次作用于每个元素，x 为元素的值，然后根据返回值是 true 还是 false 决定保留还是丢弃该元素。

意思就是遍历当前数组，当遍历到某个元素时，返回值为 false 就将该元素从数组中剔除。

filter() 方法创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素。

**3、 => 则是一种简写方法。**

```javascript
x => x * x 
```

相当于：

```javascript
function(x){return x*x}
```

所以 **x => b.has(x)** 本质是一个函数相当于 **function(x){return b.has(x)}**。

**4、b.has(x)**

Set.has(x) 是 set 中的一个方法。即判断当前 set 中是否含有 x，如果有返回 true，没有返回 false。

所以这段程序也可以写成：

```javascript
var a = new Set([1, 2, 3]); 
var b = new Set([4, 3, 2]); 
var arr = [...a];            //将a转换成数组
var fArr = arr.filter(function(x){    //使用filter过滤数组，并将新数组返回到fArr
    return b.has(x);        //判断b中是否含有a中的元素，没有则返回false
})
var intersect = new Set(fArr);          //将fArr转换成set
console.log(fArr);
```

#### 3.1.2 ES6 Reflect 与 Proxy

Proxy 与 Reflect 是 ES6 为了操作对象引入的 API 。

Proxy 可以对目标对象的读取、函数调用等操作进行拦截，然后进行操作处理。它不直接操作对象，而是像代理模式，通过对象的代理对象进行操作，在进行这些操作时，可以添加一些需要的额外操作。

Reflect 可以用于获取目标对象的行为，它与 Object 类似，但是更易读，为操作对象提供了一种更优雅的方式。它的方法与 Proxy 是对应的。

##### 3.1.2.1 Proxy

一个 Proxy 对象由两个部分组成： target 、 handler 。在通过 Proxy 构造函数生成实例对象时，需要提供这两个参数。 target 即目标对象， handler 是一个对象，声明了代理 target 的指定行为。

```javascript
let target = {
    name: 'Tom',
    age: 24
}
let handler = {
    get: function(target, key) {
        console.log('getting '+key);
        return target[key]; // 不是target.key
    },
    set: function(target, key, value) {
        console.log('setting '+key);
        target[key] = value;
    }
}
let proxy = new Proxy(target, handler)
proxy.name     // 实际执行 handler.get
proxy.age = 25 // 实际执行 handler.set
// getting name
// setting age
// 25
```

请看上面代码，target参数就是要拦截的目标对象，handler参数就是拦截对应的操作。

target 可以为空对象

```javascript
let targetEpt = {}
let proxyEpt = new Proxy(targetEpt, handler)
// 调用 get 方法，此时目标对象为空，没有 name 属性
proxyEpt.name // getting name
// 调用 set 方法，向目标对象中添加了 name 属性
proxyEpt.name = 'Tom'
// setting name
// "Tom"
// 再次调用 get ，此时已经存在 name 属性
proxyEpt.name
// getting name
// "Tom"

// 通过构造函数新建实例时其实是对目标对象进行了浅拷贝，因此目标对象与代理对象会互相影响
targetEpt
// {name: "Tom"}
```

handler 对象也可以为空，相当于不设置拦截操作，直接访问目标对象

```javascript
let targetEmpty = {}
let proxyEmpty = new Proxy(targetEmpty,{})
proxyEmpty.name = "Tom"
targetEmpty // {name: "Tom"}
```

###### 3.1.2.1.1 get(target, propKey, receiver) 方法

用于拦截目标的读取操作，接受三个参数，target表示目标对象、propKey表示属性名、receiver表示proxy实例本身。第三个参数非必须。

```javascript
let exam ={
    name: "Tom",
    age: 24
}
let proxy = new Proxy(exam, {
  get(target, propKey, receiver) {
    console.log('Getting ' + propKey);
    return target[propKey];
  }
})
proxy.name 
// Getting name
// "Tom"
```

get() 方法可以继承。

```javascript
let proxy = new Proxy({}, {
  get(target, propKey, receiver) {
      // 实现私有属性读取保护
      if(propKey[0] === '_'){
          throw new Erro(`Invalid attempt to get private     "${propKey}"`);
      }
      console.log('Getting ' + propKey);
      return target[propKey];
  }
});
 
let obj = Object.create(proxy);
obj.name
// Getting name
```

###### 3.1.2.1.2 set(target, propKey, value, receiver) 方法

用于拦截 target 对象上的 propKey 的赋值操作。接受四个参数，target表示目标对象、propKey表示属性名、value表示属性值、receiver表示proxy实例本身。第四个参数非必须。

如果目标对象自身的某个属性，不可写且不可配置，那么set方法将不起作用。

```javascript
let validator = {
    set: function(obj, prop, value) {
        if (prop === 'age') {
            if (!Number.isInteger(value)) {
                throw new TypeError('The age is not an integer');
            }
            if (value > 200) {
                throw new RangeError('The age seems invalid');
            }
        }
        // 对于满足条件的 age 属性以及其他属性，直接保存
        obj[prop] = value;
    }
};
let proxy= new Proxy({}, validator)
proxy.age = 100;
proxy.age           // 100
proxy.age = 'oppps' // 报错
proxy.age = 300     // 报错
```

第四个参数 receiver 表示原始操作行为所在对象，一般是 Proxy 实例本身。

```javascript
const handler = {
    set: function(obj, prop, value, receiver) {
        obj[prop] = receiver;
    }
};
const proxy = new Proxy({}, handler);
proxy.name= 'Tom';
proxy.name=== proxy // true
 
const exam = {}
Object.setPrototypeOf(exam, proxy)
exam.name = "Tom"
exam.name === exam // true
```

注意，严格模式下，set代理如果没有返回true，就会报错。

###### 3.1.2.1.3 apply(target, ctx, args) 方法

用于拦截函数的调用、call 和 reply 操作。target 表示目标对象，ctx 表示目标对象上下文，args 表示目标对象的参数数组。

每当执行`proxy`函数（直接调用或`call`和`apply`调用），就会被`apply`方法拦截。

```javascript
function sub(a, b){
    return a - b;
}
let handler = {
    apply: function(target, ctx, args){
        console.log('handle apply');
        return Reflect.apply(...arguments);
    }
}
let proxy = new Proxy(sub, handler)
proxy(2, 1) 
// handle apply
// 1
```

###### 3.1.2.1.4 has(target, propKey) 方法

用于拦截 HasProperty 操作，即在判断 target 对象是否存在 propKey 属性时，会被这个方法拦截。此方法不判断一个属性是对象自身的属性，还是继承的属性。

```javascript
let  handler = {
    has: function(target, propKey){
        console.log("handle has");
        return propKey in target;
    }
}
let exam = {name: "Tom"}
let proxy = new Proxy(exam, handler)
'name' in proxy
// handle has
// true
```

注意：此方法不拦截 for ... in 循环。

###### 3.1.2.1.5 construct(target, args) 方法

用于拦截 new 命令。接受三个参数，分别是目标对象、构造函数参数数组，new命令作用的构造函数。construct返回的必须是对象，否则会报错。因为construct拦截的是构造函数，他的目标对象必须是函数，否则也会报错。construct方法中this指向hanldler，不是实例对象。

```javascript
let handler = {
    construct: function (target, args, newTarget) {
        console.log('handle construct')
        return Reflect.construct(target, args, newTarget)  
    }
}
class Exam { 
    constructor (name) {  
        this.name = name 
    }
}
let ExamProxy = new Proxy(Exam, handler)
let proxyObj = new ExamProxy('Tom')
console.log(proxyObj)
// handle construct
// exam {name: "Tom"}
```

###### 3.1.2.1.6 deleteProperty(target, propKey) 方法

用于拦截 delete 操作，如果这个方法抛出错误或者返回 false ，propKey 属性就无法被 delete 命令删除。

```javascript
let handler = {
  deleteProperty(target, propKey) {
    if (propKey[0] === "_") {
      throw new Error(
        `Invalid attempt to delete private "${propKey}" property`
      );
    }
    delete target[propKey];
    return true;
  },
};
let proxy = new Proxy({ name: "Tom", _age: 2 }, handler);
delete proxy.name;
console.log(proxy.name); // undefined
delete proxy._age; // Error: Invalid attempt to delete private "_age" property
```

###### 3.1.2.1.7 defineProperty(target, propKey, propDesc) 方法

用于拦截 Object.defineProperty 方法的操作。若目标对象不可扩展，增加目标对象上不存在的属性会报错；若属性不可写或不可配置，则不能改变这些属性。

```javascript
let handler = {
    defineProperty: function(target, propKey, propDesc){
        console.log("handle defineProperty");
        return true;
    }
}
let target = {}
let proxy = new Proxy(target, handler)
proxy.name = "Tom"
// handle defineProperty
target
// {name: "Tom"}
 
// defineProperty 返回值为false，添加属性操作无效
let handler1 = {
    defineProperty: function(target, propKey, propDesc){
        console.log("handle defineProperty");
        return false;
    }
}
let target1 = {}
let proxy1 = new Proxy(target1, handler1)
proxy1.name = "Jerry"
target1
// {}
```

###### 3.1.2.1.8 getOwnPropertyDescriptor(target, propKey) 方法

用于拦截 Object.getOwnPropertyDescriptor() 返回值为属性描述对象或者 undefined 。

```javascript
let handler = {
    getOwnPropertyDescriptor: function(target, propKey){
        return Object.getOwnPropertyDescriptor(target, propKey);
    }
}
let target = {name: "Tom"}
let proxy = new Proxy(target, handler)
Object.getOwnPropertyDescriptor(proxy, 'name')
// {value: "Tom", writable: true, enumerable: true, configurable: true}
```

###### 3.1.2.1.9 getPrototypeOf(target) 方法

主要用于拦截获取对象原型的操作。包括以下操作：

- Object.prototype._proto_
- Object.prototype.isPrototypeOf()
- Object.getPrototypeOf()
- Reflect.getPrototypeOf()
- instanceof

```javascript
let exam = {}
let proxy = new Proxy({},{
    getPrototypeOf: function(target){
        return exam;
    }
})
Object.getPrototypeOf(proxy) // {}
```

注意，返回值必须是对象或者 null ，否则报错。另外，如果目标对象不可扩展（non-extensible），getPrototypeOf 方法必须返回目标对象的原型对象。

```javascript
let proxy = new Proxy({},{
    getPrototypeOf: function(target){
        return true;
    }
})
Object.getPrototypeOf(proxy)
// TypeError: 'getPrototypeOf' on proxy: trap returned neither object // nor null
```

###### 3.1.2.1.10 isExtensible(target) 方法

用于拦截 Object.isExtensible 操作。

该方法只能返回布尔值，否则返回值会被自动转为布尔值。

```javascript
let proxy = new Proxy({},{
    isExtensible:function(target){
        return true;
    }
})
Object.isExtensible(proxy) // true
```

注意：它的返回值必须与目标对象的isExtensible属性保持一致，否则会抛出错误。

```javascript
let proxy = new Proxy({},{
    isExtensible:function(target){
        return false;
    }
})
Object.isExtensible(proxy)
// TypeError: 'isExtensible' on proxy: trap result does not reflect 
// extensibility of proxy target (which is 'true')
```

###### 3.1.2.1.11 ownKeys(target) 方法

用于拦截对象自身属性的读取操作。主要包括以下操作：

- Object.getOwnPropertyNames()
- Object.getOwnPropertySymbols()
- Object.keys()
- or...in

方法返回的数组成员，只能是字符串或 Symbol 值，否则会报错。

若目标对象中含有不可配置的属性，则必须将这些属性在结果中返回，否则就会报错。

若目标对象不可扩展，则必须全部返回且只能返回目标对象包含的所有属性，不能包含不存在的属性，否则也会报错。

```javascript
let proxy = new Proxy( {
  name: "Tom",
  age: 24
}, {
    ownKeys(target) {
        return ['name'];
    }
});
Object.keys(proxy)
// [ 'name' ]f返回结果中，三类属性会被过滤：
//          - 目标对象上没有的属性
//          - 属性名为 Symbol 值的属性
//          - 不可遍历的属性
 
let target = {
  name: "Tom",
  [Symbol.for('age')]: 24,
};
// 添加不可遍历属性 'gender'
Object.defineProperty(target, 'gender', {
  enumerable: false,
  configurable: true,
  writable: true,
  value: 'male'
});
let handler = {
    ownKeys(target) {
        return ['name', 'parent', Symbol.for('age'), 'gender'];
    }
};
let proxy = new Proxy(target, handler);
Object.keys(proxy)
// ['name']
```

###### 3.1.2.1.12 preventExtensions(target) 方法

拦截 Object.preventExtensions 操作。该方法必须返回一个布尔值，否则会自动转为布尔值。

```javascript
// 只有目标对象不可扩展时（即 Object.isExtensible(proxy) 为 false ），
// proxy.preventExtensions 才能返回 true ，否则会报错
var proxy = new Proxy({}, {
  preventExtensions: function(target) {
    return true;
  }
});
// 由于 proxy.preventExtensions 返回 true，此处也会返回 true，因此会报错
Object.preventExtensions(proxy) 被// TypeError: 'preventExtensions' on proxy: trap returned truish but // the proxy target is extensible
 
// 解决方案
 var proxy = new Proxy({}, {
  preventExtensions: function(target) {
    // 返回前先调用 Object.preventExtensions
    Object.preventExtensions(target);
    return true;
  }
});
Object.preventExtensions(proxy)
// Proxy {}
```

###### 3.1.2.1.13 setPrototypeOf 方法

主要用来拦截 Object.setPrototypeOf 方法。

返回值必须为布尔值，否则会被自动转为布尔值。

若目标对象不可扩展，setPrototypeOf 方法不得改变目标对象的原型。

```javascript
let proto = {}
let proxy = new Proxy(function () {}, {
    setPrototypeOf: function(target, proto) {
        console.log("setPrototypeOf");
        return true;
    }
}
);
Object.setPrototypeOf(proxy, proto);
// setPrototypeOf
```

###### 3.1.2.1.14 Proxy.revocable()

Proxy.revocable()方法返回一个对象，该对象的`proxy`属性是`Proxy`实例，`revoke`属性是一个函数，可以取消`Proxy`实例。

```javascript
let {proxy, revoke} = Proxy.revocable({}, {});
proxy.name = "Tom";
revoke();
proxy.name 
// TypeError: Cannot perform 'get' on a proxy that has been revoked

let target = { name: "Tom" },
  	handler = {};
let { proxy, revoke } = Proxy.revocable(target, handler);
console.log(proxy.name); // Tom
revoke();
console.log(proxy.name); // TypeError: Cannot perform 'get' on a proxy that has been revoked
```

##### 3.1.2.2 Reflect

ES6 中将 Object 的一些明显属于语言内部的方法移植到了 Reflect 对象上（当前某些方法会同时存在于 Object 和 Reflect 对象上），未来的新方法会只部署在 Reflect 对象上。

Reflect 对象对某些方法的返回结果进行了修改，使其更合理。

Reflect 对象使用函数的方式实现了 Object 的命令式操作。

Reflect对象一共有13个静态方法。

- Reflect.apply(target, thisArg, args)
- Reflect.construct(target, args)
- Reflect.get(target, name, receiver)
- Reflect.set(target, name, value, receiver)
- Reflect.defineProperty(target, name, desc)
- Reflect.deleteProperty(target, name)
- Reflect.has(target, name)
- Reflect.ownKeys(target)
- Reflect.isExtensible(target)
- Reflect.preventExtensions(target)
- Reflect.getOwnPropertyDescriptor(target, name)
- Reflect.getPrototypeOf(target)
- Reflect.setPrototypeOf(target, prototype)

这些方法的作用，大部分与Object对象的同名方法的作用都是相同的，而且它与Proxy对象的方法是一一对应的。

###### 3.1.2.2.1 Reflect.get(target, name, receiver)

Refect.get 方法查找并返回 target 对象的 name 属性，如果没有，则返回undefined。

当 name 属性部署了 getter 方法，则 getter 方法的 this 会绑定 receiver。

如果第一个参数不是对象，Refect.get 方法会报错。

```javascript
let exam = {
    name: "Tom",
    age: 24,
    get info(){
        return this.name + this.age;
    }
}
Reflect.get(exam, 'name'); // "Tom"
 
// 当 target 对象中存在 name 属性的 getter 方法， getter 方法的 this 会绑定 receiver
let receiver = {
    name: "Jerry",
    age: 20
}
Reflect.get(exam, 'info', receiver); // Jerry20
 
// 当 name 为不存在于 target 对象的属性时，返回 undefined
Reflect.get(exam, 'birth'); // undefined
 
// 当 target 不是对象时，会报错
Reflect.get(1, 'name'); // TypeError
```

###### 3.1.2.2.2 Reflect.set(target, name, value, receiver)

将 target 的 name 属性设置为 value。返回值为 boolean ，true 表示修改成功，false 表示失败。当 target 为不存在的对象时，会报错。

当 name 属性设置了 setter 方法，则 setter 方法的 this 会绑定 receiver。value 为空时会将 name 属性清除。如果第一个参数不是对象，Refect.set 方法会报错。

```javascript
let exam = {
    name: "Tom",
    age: 24,
    set info(value){
        return this.age = value;
    }
}
exam.age; // 24
Reflect.set(exam, 'age', 25); // true
exam.age; // 25
 
// value 为空时会将 name 属性清除
Reflect.set(exam, 'age', ); // true
exam.age; // undefined
 
// 当 target 对象中存在 name 属性 setter 方法时，setter 方法中的 this 会绑定 receiver , 所以修改的实际上是 receiver 的属性,
let receiver = {
    age: 18
}
Reflect.set(exam, 'info', 1, receiver); // true
receiver.age; // 1
 
let receiver1 = {
    name: 'oppps'
}
Reflect.set(exam, 'info', 1, receiver1);
receiver1.age; // 1
```

###### 3.1.2.2.3 Reflect.has(obj, name)

是 name in obj 指令的函数化，用于查找 name 属性在 obj 对象中是否存在。返回值为 boolean。如果 obj 不是对象则会报错 TypeError。

```javascript
let exam = {
    name: "Tom",
    age: 24
}
Reflect.has(exam, 'name'); // true
```

###### 3.1.2.2.4 Reflect.deleteProperty(obj, property)

是 delete obj[property] 的函数化，用于删除 obj 对象的 property 属性，返回值为 boolean。如果 obj 不是对象则会报错 TypeError。

```javascript
let exam = {
    name: "Tom",
    age: 24
}
Reflect.deleteProperty(exam , 'name'); // true
exam // {age: 24} 
// property 不存在时，也会返回 true
Reflect.deleteProperty(exam , 'name'); // true
```

###### 3.1.2.2.5 Reflect.construct(obj, args)

等同于 new target(...args)。

```javascript
function exam(name){
    this.name = name;
}
Reflect.construct(exam, ['Tom']); // exam {name: "Tom"}
```

###### 3.1.2.2.6 Reflect.getPrototypeOf(obj)

用于读取 obj 的 `_proto_` 属性。在 obj 不是对象时不会像 Object 一样把 obj 转为对象，而是会报错。

```javascript
class Exam{}
let obj = new Exam()
Reflect.getPrototypeOf(obj) === Exam.prototype // true
```

###### 3.1.2.2.7 Reflect.setPrototypeOf(obj, newProto)

用于设置目标对象的 prototype。

```javascript
let obj ={}
Reflect.setPrototypeOf(obj, Array.prototype); // true
```

###### 3.1.2.2.8 Reflect.apply(func, thisArg, args)

等同于 Function.prototype.apply.call(func, thisArg, args) 。func 表示目标函数；thisArg 表示目标函数绑定的 this 对象；args 表示目标函数调用时传入的参数列表，可以是数组或类似数组的对象。若目标函数无法调用，会抛出 TypeError 。

```javascript
Reflect.apply(Math.max, Math, [1, 3, 5, 3, 1]); // 5
```

###### 3.1.2.2.9 Reflect.defineProperty(target, propertyKey, attributes)

用于为目标对象定义属性。如果 target 不是对象，会抛出错误。

```javascript
let myDate= {}
Reflect.defineProperty(MyDate, 'now', {
  value: () => Date.now()
}); // true
 
const student = {};
Reflect.defineProperty(student, "name", {value: "Mike"}); // true
student.name; // "Mike"
```

###### 3.1.2.2.10 Reflect.getOwnPropertyDescriptor(target, propertyKey)

用于得到 target 对象的 propertyKey 属性的描述对象。在 target 不是对象时，会抛出错误表示参数非法，不会将非对象转换为对象。

```javascript
var exam = {}
Reflect.defineProperty(exam, 'name', {
  value: true,
  enumerable: false,
})
Reflect.getOwnPropertyDescriptor(exam, 'name')
// { configurable: false, enumerable: false, value: true, writable:
// false}
 
 
// propertyKey 属性在 target 对象中不存在时，返回 undefined
Reflect.getOwnPropertyDescriptor(exam, 'age') // undefined
```

###### 3.1.2.2.11 Reflect.isExtensible(target)

用于判断 target 对象是否可扩展。返回值为 boolean 。如果 target 参数不是对象，会抛出错误。

```javascript
let exam = {}
Reflect.isExtensible(exam) // true
```

###### 3.1.2.2.12 Reflect.preventExtensions(target)

用于让 target 对象变为不可扩展。如果 target 参数不是对象，会抛出错误。

```javascript
let exam = {}
Reflect.preventExtensions(exam) // true
```

###### 3.1.2.2.13 Reflect.ownKeys(target)

用于返回 target 对象的所有属性，等同于 Object.getOwnPropertyNames 与Object.getOwnPropertySymbols 之和。

```javascript
var exam = {
  name: 1,
  [Symbol.for('age')]: 4
}
Reflect.ownKeys(exam) // ["name", Symbol(age)]
```

##### 3.1.2.3 组合使用

Reflect 对象的方法与 Proxy 对象的方法是一一对应的。所以 Proxy 对象的方法可以通过调用 Reflect 对象的方法获取默认行为，然后进行额外操作。

```javascript
let exam = {
    name: "Tom",
    age: 24
}
let handler = {
    get: function(target, key){
        console.log("getting "+key);
        return Reflect.get(target,key);
    },
    set: function(target, key, value){
        console.log("setting "+key+" to "+value)
        Reflect.set(target, key, value);
    }
}
let proxy = new Proxy(exam, handler)
proxy.name = "Jerry"
proxy.name
// setting name to Jerry
// getting name
// "Jerry"
```

##### 3.1.2.4 使用场景拓展

**实现观察者模式**

```javascript
// 定义 Set 集合
const queuedObservers = new Set();
// 把观察者函数都放入 Set 集合中
const observe = fn => queuedObservers.add(fn);
// observable 返回原始对象的代理，拦截赋值操作
const observable = obj => new Proxy(obj, {set});
function set(target, key, value, receiver) {
  // 获取对象的赋值操作
  const result = Reflect.set(target, key, value, receiver);
  // 执行所有观察者
  queuedObservers.forEach(observer => observer());
  // 执行赋值操作
  return result;
}
```

#### 3.2.1 ES6 字符串

##### 3.2.1.1 子串的识别

ES6 之前判断字符串是否包含子串，用 indexOf 方法，ES6 新增了子串的识别方法。

- **includes()**：返回布尔值，判断是否找到参数字符串。
- **startsWith()**：返回布尔值，判断参数字符串是否在原字符串的头部。
- **endsWith()**：返回布尔值，判断参数字符串是否在原字符串的尾部。

以上三个方法都可以接受两个参数，需要搜索的字符串，和可选的搜索起始位置索引。

```javascript
let string = "apple,banana,orange";
string.includes("banana");     // true
string.startsWith("apple");    // true
string.endsWith("apple");      // false
string.startsWith("banana",6)  // true
```

- 这三个方法只返回布尔值，如果需要知道子串的位置，还是得用 indexOf 和 lastIndexOf 。
- 这三个方法如果传入了正则表达式而不是字符串，会抛出错误。而 indexOf 和 lastIndexOf 这两个方法，它们会将正则表达式转换为字符串并搜索它。

##### 3.2.1.2 字符串重复

repeat()：返回新的字符串，表示将字符串重复指定次数返回。

```javascript
console.log("Hello,".repeat(2));  // "Hello,Hello,"
```

如果参数是小数，向下取整

```javascript
console.log("Hello,".repeat(3.2));  // "Hello,Hello,Hello,"
```

如果参数是 0 至 -1 之间的小数，会进行取整运算，0 至 -1 之间的小数取整得到 -0 ，等同于 repeat 零次

```javascript
console.log("Hello,".repeat(-0.5));  // "" 
```

如果参数是 NaN，等同于 repeat 零次

```javascript
console.log("Hello,".repeat(NaN));  // "" 
```

如果参数是负数或者 Infinity ，会报错：

```javascript
console.log("Hello,".repeat(-1));  
// RangeError: Invalid count value

console.log("Hello,".repeat(Infinity));  
// RangeError: Invalid count value
```

如果传入的参数是字符串，则会先将字符串转化为数字

```javascript
console.log("Hello,".repeat("hh")); // ""
console.log("Hello,".repeat("2"));  // "Hello,Hello,"
```

##### 3.2.1.3 字符串补全

- **padStart**：返回新的字符串，表示用参数字符串从头部（左侧）补全原字符串。
- **padEnd**：返回新的字符串，表示用参数字符串从尾部（右侧）补全原字符串。

以上两个方法接受两个参数，第一个参数是指定生成的字符串的最小长度，第二个参数是用来补全的字符串。如果没有指定第二个参数，默认用空格填充。

```javascript
console.log("h".padStart(5,"o"));  // "ooooh"
console.log("h".padEnd(5,"o"));    // "hoooo"
console.log("h".padStart(5));      // "    h"
```

如果指定的长度小于或者等于原字符串的长度，则返回原字符串：

```javascript
console.log("hello".padStart(5,"A"));  // "hello"
```

如果原字符串加上补全字符串长度大于指定长度，则截去超出位数的补全字符串：

```javascript
console.log("hello".padEnd(10,",world!"));  // "hello,worl"
```

常用于补全位数：

```javascript
console.log("123".padStart(10,"0"));  // "0000000123"
```

##### 3.2.1.4 模板字符串

模板字符串相当于加强版的字符串，用反引号 **`**，除了作为普通字符串，还可以用来定义多行字符串，还可以在字符串中加入变量和表达式。

###### 3.2.1.4.1 普通字符串

```javascript
let string = `Hello'\n'world`;
console.log(string); 
// "Hello'
// 'world"
```

###### 3.2.1.4.2 多行字符串

```javascript
let string1 =  `Hey,
can you stop angry now?`;
console.log(string1);
// Hey,
// can you stop angry now?
```

###### 3.2.1.4.3 字符串插入变量和表达式

变量名写在 `${}` 中，`${}` 中可以放入 JavaScript 表达式。

```javascript
let name = "Mike";
let age = 27;
let info = `My Name is ${name},I am ${age+1} years old next year.`
console.log(info);
// My Name is Mike,I am 28 years old next year.
```

###### 3.2.1.4.4 字符串中调用函数

```javascript
function f(){
  return "have fun!";
}
let string2= `Game start,${f()}`;
console.log(string2);  // Game start,have fun!
```

###### 3.2.1.4.5 注意要点

模板字符串中的换行和空格都是会被保留的

```javascript
innerHtml = `<ul>
  <li>menu</li>
  <li>mine</li>
</ul>
`;
console.log(innerHtml);
// 输出
<ul>
 <li>menu</li>
 <li>mine</li>
</ul>
```

##### 3.2.1.5 标签模板

标签模板，是一个函数的调用，其中调用的参数是模板字符串。

```javascript
alert`Hello world!`;
// 等价于
alert('Hello world!');
```

当模板字符串中带有变量，会将模板字符串参数处理成多个参数。

```javascript
function f(stringArr,...values){
 let result = "";
 for(let i=0; i<stringArr.length; i++){
  result += stringArr[i];
  if(values[i]){
   	result += values[i];
   }
  }
 return result;
}
let name = 'Mike';
let age = 27;
f`My Name is ${name},I am ${age+1} years old next year.`;
// "My Name is Mike,I am 28 years old next year."
 
f`My Name is ${name},I am ${age+1} years old next year.`;
// 等价于
f(['My Name is',',I am ',' years old next year.'],'Mike',28);
```

**应用**1：过滤 HTML 字符串，防止用户输入恶意内容。

```javascript
function f(stringArr,...values){
 let result = "";
 for(let i=0;i<stringArr.length;i++){
  result += stringArr[i];
   if(values[i]){
     result += String(values[i]).replace(/&/g, "&amp;")
               .replace(/</g, "&lt;")
               .replace(/>/g, "&gt;");
    }
 }
 return result;
}
name = '<Amy&MIke>';
f`<p>Hi, ${name}.I would like send you some message.</p>`;
// <p>Hi, &lt;Amy&amp;MIke&gt;.I would like send you some message.</p>
```

**应用2**：国际化处理（转化多国语言）

```javascript
i18n`Hello ${name}, you are visitor number ${visitorNumber}.`;  
// 你好**，你是第**位访问者
```

#### 3.2.2 ES6 数值

##### 3.2.2.1 数值的表示

二进制表示法新写法: 前缀 0b 或 0B 。

```javascript
console.log(0b11 === 3); // true
console.log(0B11 === 3); // true
```

八进制表示法新写法: 前缀 0o 或 0O 。

```javascript
console.log(0o11 === 9); // true
console.log(0O11 === 9); // true
```

##### 3.2.2.2 常量

```
Number.EPSILON
```

Number.EPSILON 属性表示 1 与大于 1 的最小浮点数之间的差。

它的值接近于 2.2204460492503130808472633361816E-16，或者 2-52。

测试数值是否在误差范围内:

```javascript
0.1 + 0.2 === 0.3; // false
// 在误差范围内即视为相等
equal = (Math.abs(0.1 - 0.3 + 0.2) < Number.EPSILON); // true
```

##### 3.2.2.3 属性特性

```
writable：false
enumerable：false
configurable：false
```

##### 3.2.2.4 最大/最小安全整数

###### 3.2.2.4.1 安全整数

安全整数表示在 JavaScript 中能够精确表示的整数，安全整数的范围在 2 的 -53 次方到 2 的 53 次方之间（不包括两个端点），超过这个范围的整数无法精确表示。

###### 3.2.2.4.2 最大安全整数

安全整数范围的上限，即 2 的 53 次方减 1 。

```javascript
Number.MAX_SAFE_INTEGER + 1 === Number.MAX_SAFE_INTEGER + 2; // true
Number.MAX_SAFE_INTEGER === Number.MAX_SAFE_INTEGER + 1;     // false
Number.MAX_SAFE_INTEGER - 1 === Number.MAX_SAFE_INTEGER - 2; // false
```

###### 3.2.2.4.3 最小安全整数

安全整数范围的下限，即 2 的 53 次方减 1 的负数。

```javascript
Number.MIN_SAFE_INTEGER + 1 === Number.MIN_SAFE_INTEGER + 2; // false 
Number.MIN_SAFE_INTEGER === Number.MIN_SAFE_INTEGER - 1;     // false 
Number.MIN_SAFE_INTEGER - 1 === Number.MIN_SAFE_INTEGER - 2; // true
```

##### 3.2.2.5 方法

###### 3.2.2.5.1 Number.isFinite()

用于检查一个数值是否为有限的（ finite ），即不是 Infinity

```javascript
console.log( Number.isFinite(1));   // true
console.log( Number.isFinite(0.1)); // true
 
// NaN 不是有限的
console.log( Number.isFinite(NaN)); // false
 
console.log( Number.isFinite(Infinity));  // false
console.log( Number.isFinite(-Infinity)); // false
 
// Number.isFinate 没有隐式的 Number() 类型转换，所有非数值都返回 false
console.log( Number.isFinite('foo')); // false
console.log( Number.isFinite('15'));  // false
console.log( Number.isFinite(true));  // false

```

###### 3.2.2.5.2 Number.isNaN()

用于检查一个值是否为 NaN 

```javascript
console.log(Number.isNaN(NaN));      // true
console.log(Number.isNaN('true'/0)); // true
 
// 在全局的 isNaN() 中，以下皆返回 true，因为在判断前会将非数值向数值转换
// 而 Number.isNaN() 不存在隐式的 Number() 类型转换，非 NaN 全部返回 false
Number.isNaN("NaN");      // false
Number.isNaN(undefined);  // false
Number.isNaN({});         // false
Number.isNaN("true");     // false
```

###### 3.2.2.5.3 Number.parseInt()

用于将给定字符串转化为指定进制的整数。

```javascript
// 不指定进制时默认为 10 进制
Number.parseInt('12.34'); // 12
Number.parseInt(12.34);   // 12
 
// 指定进制
Number.parseInt('0011',2); // 3
 
// 与全局的 parseInt() 函数是同一个函数
Number.parseInt === parseInt; // true
```

###### 3.2.2.5.4 Number.parseFloat()

用于把一个字符串解析成浮点数。

```javascript
Number.parseFloat('123.45')    // 123.45
Number.parseFloat('123.45abc') // 123.45
 
// 无法被解析成浮点数，则返回 NaN
Number.parseFloat('abc') // NaN
 
// 与全局的 parseFloat() 方法是同一个方法
Number.parseFloat === parseFloat // true
```

###### 3.2.2.5.5 Number.isInteger()

用于判断给定的参数是否为整数。

```javascript
Number.isInteger(value)
Number.isInteger(0); // true
// JavaScript 内部，整数和浮点数采用的是同样的储存方法,因此 1 与 1.0 被视为相同的值
Number.isInteger(1);   // true
Number.isInteger(1.0); // true
 
Number.isInteger(1.1);     // false
Number.isInteger(Math.PI); // false
 
// NaN 和正负 Infinity 不是整数
Number.isInteger(NaN);       // false
Number.isInteger(Infinity);  // false
Number.isInteger(-Infinity); // false
 
Number.isInteger("10");  // false
Number.isInteger(true);  // false
Number.isInteger(false); // false
Number.isInteger([1]);   // false
 
// 数值的精度超过 53 个二进制位时，由于第 54 位及后面的位被丢弃，会产生误判
Number.isInteger(1.0000000000000001) // true
 
// 一个数值的绝对值小于 Number.MIN_VALUE（5E-324），即小于 JavaScript 能够分辨
// 的最小值，会被自动转为 0，也会产生误判
Number.isInteger(5E-324); // false
Number.isInteger(5E-325); // true
```

###### 3.2.2.5.6 Number.isSafeInteger()

用于判断数值是否在安全范围内。

```javascript
Number.isSafeInteger(Number.MIN_SAFE_INTEGER - 1); // false
Number.isSafeInteger(Number.MAX_SAFE_INTEGER + 1); // false
```

##### 3.2.2.6 Math 对象的扩展

ES6 在 Math 对象上新增了 17 个数学相关的静态方法，这些方法只能在 Math 中调用。

###### 3.2.2.6.1 Math.cbrt()

用于计算一个数的立方根。

```javascript
Math.cbrt(1);  // 1
Math.cbrt(0);  // 0
Math.cbrt(-1); // -1
// 会对非数值进行转换
Math.cbrt('1'); // 1
 
// 非数值且无法转换为数值时返回 NaN
Math.cbrt('hhh'); // NaN
```

###### 3.2.2.6.2 Math.imul()

两个数以 32 位带符号整数形式相乘的结果，返回的也是一个 32 位的带符号整数。

```javascript
// 大多数情况下，结果与 a * b 相同 
Math.imul(1, 2);   // 2
 
// 用于正确返回大数乘法结果中的低位数值
Math.imul(0x7fffffff, 0x7fffffff); // 1
```

###### 3.2.2.6.3 Math.hypot()

用于计算所有参数的平方和的平方根。

```javascript
Math.hypot(3, 4); // 5
 
// 非数值会先被转换为数值后进行计算
Math.hypot(1, 2, '3'); // 3.741657386773941
Math.hypot(true);      // 1
Math.hypot(false);     // 0
 
// 空值会被转换为 0
Math.hypot();   // 0
Math.hypot([]); // 0
 
// 参数为 Infinity 或 -Infinity 返回 Infinity
Math.hypot(Infinity); // Infinity
Math.hypot(-Infinity); // Infinity
 
// 参数中存在无法转换为数值的参数时返回 NaN
Math.hypot(NaN);         // NaN
Math.hypot(3, 4, 'foo'); // NaN
Math.hypot({});          // NaN
```

###### 3.2.2.6.4 Math.clz32()

用于返回数字的32 位无符号整数形式的前导0的个数。

```javascript
Math.clz32(0); // 32
Math.clz32(1); // 31
Math.clz32(0b01000000000100000000000000000000); // 1
 
// 当参数为小数时，只考虑整数部分
Math.clz32(0.5); // 32
 
// 对于空值或非数值，会转化为数值再进行计算
Math.clz32('1');       // 31
Math.clz32();          // 32
Math.clz32([]);        // 32
Math.clz32({});        // 32
Math.clz32(NaN);       // 32
Math.clz32(Infinity);  // 32
Math.clz32(-Infinity); // 32
Math.clz32(undefined); // 32
Math.clz32('hhh');     // 32
```

###### 3.2.2.6.5 Math.trunc()

用于返回数字的整数部分。

```javascript
Math.trunc(12.3); // 12
Math.trunc(12);   // 12
 
// 整数部分为 0 时也会判断符号
Math.trunc(-0.5); // -0
Math.trunc(0.5);  // 0
 
// Math.trunc 会将非数值转为数值再进行处理
Math.trunc("12.3"); // 12
 
// 空值或无法转化为数值时时返回 NaN
Math.trunc();           // NaN
Math.trunc(NaN);        // NaN
Math.trunc("hhh");      // NaN
Math.trunc("123.2hhh"); // NaN
```

###### 3.2.2.6.6 Math.fround()

用于获取数字的32位单精度浮点数形式。

```javascript
// 对于 2 的 24 次方取负至 2 的 24 次方之间的整数（不含两个端点），返回结果与参数本身一致
Math.fround(-(2**24)+1);  // -16777215
Math.fround(2 ** 24 - 1); // 16777215
 
// 用于将 64 位双精度浮点数转为 32 位单精度浮点数
Math.fround(1.234) // 1.125
// 当小数的精度超过 24 个二进制位，会丢失精度
Math.fround(0.3); // 0.30000001192092896
// 参数为 NaN 或 Infinity 时返回本身
Math.fround(NaN)      // NaN
Math.fround(Infinity) // Infinity
 
// 参数为其他非数值类型时会将参数进行转换 
Math.fround('5');  // 5
Math.fround(true); // 1
Math.fround(null); // 0
Math.fround([]);   // 0
Math.fround({});   // NaN
```

###### 3.2.2.6.7 Math.sign()

判断数字的符号（正、负、0）。

```javascript
Math.sign(1);  // 1
Math.sign(-1); // -1
 
// 参数为 0 时，不同符号的返回不同
Math.sign(0);  // 0
Math.sign(-0); // -0
 
// 判断前会对非数值进行转换
Math.sign('1');  // 1
Math.sign('-1'); // -1  
 
// 参数为非数值（无法转换为数值）时返回 NaN
Math.sign(NaN);   // NaN 
Math.sign('hhh'); // NaN
```

###### 3.2.2.6.8 Math.expm1()

用于计算 e 的 x 次方减 1 的结果，即 Math.exp(x) - 1 。

```javascript
Math.expm1(1);  // 1.718281828459045
Math.expm1(0);  // 0
Math.expm1(-1); // -0.6321205588285577
// 会对非数值进行转换
Math.expm1('0'); //0
 
// 参数不为数值且无法转换为数值时返回 NaN
Math.expm1(NaN); // NaN
```

###### 3.2.2.6.9 Math.log1p(x)

用于计算1 + x 的自然对数，即 Math.log(1 + x) 。

```javascript
Math.log1p(1);  // 0.6931471805599453
Math.log1p(0);  // 0
Math.log1p(-1); // -Infinity
 
// 参数小于 -1 时返回 NaN
Math.log1p(-2); // NaN
```

###### 3.2.2.6.10 Math.log10(x)

用于计算以 10 为底的 x 的对数。

```javascript
Math.log10(1);   // 0
// 计算前对非数值进行转换
Math.log10('1'); // 0
// 参数为0时返回 -Infinity
Math.log10(0);   // -Infinity
// 参数小于0或参数不为数值（且无法转换为数值）时返回 NaN
Math.log10(-1);  // NaN
```

###### 3.2.2.6.11 Math.log2()

用于计算 2 为底的 x 的对数。

```javascript
Math.log2(1);   // 0
// 计算前对非数值进行转换
Math.log2('1'); // 0
// 参数为0时返回 -Infinity
Math.log2(0);   // -Infinity
// 参数小于0或参数不为数值（且无法转换为数值）时返回 NaN
Math.log2(-1);  // NaN
```

###### 3.2.2.6.12 指数运算符

```javascript
1 ** 2; // 1
// 右结合，从右至左计算
2 ** 2 ** 3; // 256
// **=
let exam = 2;
exam ** = 2; // 4
```

###### 3.2.2.6.13 其他运算符

- Math.sinh(x): 用于计算双曲正弦。
- Math.cosh(x): 用于计算双曲余弦。
- Math.tanh(x): 用于计算双曲正切。
- Math.asinh(x): 用于计算反双曲正弦。
- Math.acosh(x): 用于计算反双曲余弦。
- Math.atanh(x): 用于计算反双曲正切。

#### 3.2.3 ES6 对象

##### 3.2.3.1 对象字面量

###### 3.2.3.1.1 属性的简洁表示法

ES6允许对象的属性直接写变量，这时候属性名是变量名，属性值是变量值。

```javascript
const age = 12;
const name = "Amy";
const person = {age, name};
person   //{age: 12, name: "Amy"}
//等同于
const person = {age: age, name: name}
```

###### 3.2.3.1.2 方法名也可以简写

```javascript
const person = {
  sayHi(){
    console.log("Hi");
  }
}
person.sayHi();  //"Hi"
//等同于
const person = {
  sayHi:function(){
    console.log("Hi");
  }
}
person.sayHi();//"Hi"
```

如果是 Generator 函数，则要在前面加一个星号：

```javascript
const obj = {
  * myGenerator() {
    yield 'hello world';
  }
};
//等同于
const obj = {
  myGenerator: function* () {
    yield 'hello world';
  }
};
```

###### 3.2.3.1.3 属性名表达式

ES6允许用表达式作为属性名，但是一定要将表达式放在方括号内。

```javascript
const obj = {
 ["he"+"llo"](){
   return "Hi";
  }
}
obj.hello();  //"Hi"
```

注意点：属性的简洁表示法和属性名表达式不能同时使用，否则会报错。

```javascript
const hello = "Hello";
const obj = {
 [hello]
};
obj  //SyntaxError: Unexpected token }
 
const hello = "Hello";
const obj = {
 [hello+"2"]:"world"
};
obj  //{Hello2: "world"}
```

##### 3.2.3.2 对象的拓展运算符

拓展运算符（...）用于取出参数对象所有可遍历属性然后拷贝到当前对象。

###### 3.2.3.2.1 基本用法

```javascript
let person = {name: "Amy", age: 15};
let someone = { ...person };
someone;  //{name: "Amy", age: 15}
```

###### 3.2.3.2.2 可用于合并两个对象

```javascript
let age = {age: 15};
let name = {name: "Amy"};
let person = {...age, ...name};
person;  //{age: 15, name: "Amy"}
```

自定义的属性和拓展运算符对象里面属性的相同的时候：**自定义的属性在拓展运算符后面，则拓展运算符对象内部同名的属性将被覆盖掉。**

```javascript
let person = {name: "Amy", age: 15};
let someone = { ...person, name: "Mike", age: 17};
someone;  //{name: "Mike", age: 17}
```

自定义的属性在拓展运算度前面，则变成设置新对象默认属性值。

```javascript
let person = {name: "Amy", age: 15};
let someone = {name: "Mike", age: 17, ...person};
someone;  //{name: "Amy", age: 15}
```

拓展运算符后面是空对象，没有任何效果也不会报错。

```javascript
let a = {...{}, a: 1, b: 2};
a;  //{a: 1, b: 2}
```

拓展运算符后面是null或者undefined，没有效果也不会报错。

```javascript
let b = {...null, ...undefined, a: 1, b: 2};
b;  //{a: 1, b: 2}
```

##### 3.2.3.3 对象的新方法

###### 3.2.3.3.1 Object.assign(target, source_1, ···)

用于将源对象的所有可枚举属性复制到目标对象中。

```javascript
let target = {a: 1};
let object2 = {b: 2};
let object3 = {c: 3};
Object.assign(target,object2,object3);  
// 第一个参数是目标对象，后面的参数是源对象
target;  // {a: 1, b: 2, c: 3
```

- 如果目标对象和源对象有同名属性，或者多个源对象有同名属性，则后面的属性会覆盖前面的属性。
- 如果该函数只有一个参数，当参数为对象时，直接返回该对象；当参数不是对象时，会先将参数转为对象然后返回。

```javascript
Object.assign(3);         // Number {3}
typeof Object.assign(3);  // "object"
```

因为 null 和 undefined 不能转化为对象，所以会报错：

```javascript
Object.assign(null);       // TypeError: Cannot convert undefined or null to object
Object.assign(undefined);  // TypeError: Cannot convert undefined or null to object
当参数不止一个时，null 和 undefined 不放第一个，即不为目标对象时，会跳过 null 和 undefined ，不报错
Object.assign(1,undefined);  // Number {1}
Object.assign({a: 1},null);  // {a: 1}
 
Object.assign(undefined,{a: 1});  // TypeError: Cannot convert undefined or null to object
```

**注意**：assign 的属性拷贝是浅拷贝：

```javascript
let sourceObj = { a: { b: 1}};
let targetObj = {c: 3};
Object.assign(targetObj, sourceObj);
targetObj.a.b = 2;
sourceObj.a.b;  // 2
```

同名属性替换

```javascript
targetObj = { a: { b: 1, c:2}};
sourceObj = { a: { b: "hh"}};
Object.assign(targetObj, sourceObj);
targetObj;  // {a: {b: "hh"}}
```

数组的处理

```javascript
Object.assign([2,3], [5]);  // [5,3]
```

会将数组处理成对象，所以先将 [2,3] 转为 {0:2,1:3} ，然后再进行属性复制，所以源对象的 0 号属性覆盖了目标对象的 0。

###### 3.2.3.3.2 Object.is(value1, value2)

用来比较两个值是否严格相等，与（===）基本类似。

```javascript
Object.is("q","q");      // true
Object.is(1,1);          // true
Object.is([1],[1]);      // false
Object.is({q:1},{q:1});  // false
```

与（===）的区别

```javascript
//一是+0不等于-0
Object.is(+0,-0);  //false
+0 === -0  //true
//二是NaN等于本身
Object.is(NaN,NaN); //true
NaN === NaN  //false
```

#### 3.2.4 ES6 数组

##### 3.2.4.1 数组创建

###### 3.2.4.1.1 Array.of()

将参数中所有值作为元素形成数组。

```javascript
console.log(Array.of(1, 2, 3, 4)); // [1, 2, 3, 4]
 
// 参数值可为不同类型
console.log(Array.of(1, '2', true)); // [1, '2', true]
 
// 参数为空时返回空数组
console.log(Array.of()); // []
```

###### 3.2.4.1.2 Array.from(arrayLike[, mapFn[, thisArg]])

将类数组对象或可迭代对象转化为数组。

```javascript
// 参数为数组,返回与原数组一样的数组
console.log(Array.from([1, 2])); // [1, 2]
 
// 参数含空位
console.log(Array.from([1, , 3])); // [1, undefined, 3]
```

**Array.from(arrayLike[, mapFn[, thisArg]])**：

返回值为转换后的数组。

想要转换的类数组对象或可迭代对象。

```javascript
console.log(Array.from([1, 2, 3])); // [1, 2, 3]
```

**mapFn**：

可选，map 函数，用于对每个元素进行处理，放入数组的是处理后的元素。

```javascript
console.log(Array.from([1, 2, 3], (n) => n * 2)); // [2, 4, 6]
```

**thisArg**：

可选，用于指定 map 函数执行时的 this 对象。

```javascript
let map = {
    do: function(n) {
        return n * 2;
    }
}
let arrayLike = [1, 2, 3];
console.log(Array.from(arrayLike, function (n){
    return this.do(n);
}, map)); // [2, 4, 6]
```

##### 3.2.4.2 类数组对象

一个类数组对象必须含有 length 属性，且元素属性名必须是数值或者可转换为数值的字符。

```javascript
let arr = Array.from({
  0: '1',
  1: '2',
  2: 3,
  length: 3
});
console.log(arr); // ['1', '2', 3]
 
// 没有 length 属性,则返回空数组
let array = Array.from({
  0: '1',
  1: '2',
  2: 3,
});
console.log(array); // []
 
// 元素属性名不为数值且无法转换为数值，返回长度为 length 元素值为 undefined 的数组  
let array1 = Array.from({
  a: 1,
  b: 2,
  length: 2
});
console.log(array1); // [undefined, undefined]
```

##### 3.2.4.3 转换可迭代对象

###### 3.2.4.3.1 转换 map

```javascript
let map = new Map();
map.set('key0', 'value0');
map.set('key1', 'value1');
console.log(Array.from(map)); // [['key0', 'value0'],['key1',
// 'value1']]
```

###### 3.2.4.3.2 转换 set

```javascript
let arr = [1, 2, 3];
let set = new Set(arr);
console.log(Array.from(set)); // [1, 2, 3]
```

###### 3.2.4.3.3 转换字符串

```javascript
let str = 'abc';
console.log(Array.from(str)); // ["a", "b", "c"]
```

##### 3.2.4.4 查找

###### 3.2.4.4.1 find()

查找数组中符合条件的元素,若有多个符合条件的元素，则返回第一个元素。

```javascript
let arr = Array.of(1, 2, 3, 4);
console.log(arr.find(item => item > 2)); // 3
 
// 数组空位处理为 undefined
console.log([, 1].find(n => true)); // undefined
```

###### 3.2.4.4.2 findIndex()

查找数组中符合条件的元素索引，若有多个符合条件的元素，则返回第一个元素索引。

```javascript
let arr = Array.of(1, 2, 1, 3);
// 参数1：回调函数
// 参数2(可选)：指定回调函数中的 this 值
console.log(arr.findIndex(item => item == 2)); // 1
 
// 数组空位处理为 undefined
console.log([, 1].findIndex(n => true)); //0
```

##### 3.2.4.5 填充

###### 3.2.4.5.1 fill()

将一定范围索引的数组元素内容填充为单个指定的值。

```javascript
let arr = Array.of(1, 2, 3, 4);
// 参数1：用来填充的值
// 参数2：被填充的起始索引
// 参数3(可选)：被填充的结束索引，默认为数组末尾
console.log(arr.fill(0,1,2)); // [1, 0, 3, 4]
```

###### 3.2.4.5.2 copyWithin()

将一定范围索引的数组元素修改为此数组另一指定范围索引的元素。

```javascript
// 参数1：被修改的起始索引
// 参数2：被用来覆盖的数据的起始索引
// 参数3(可选)：被用来覆盖的数据的结束索引，默认为数组末尾
console.log([1, 2, 3, 4].copyWithin(0,2,4)); // [3, 4, 3, 4]
 
// 参数1为负数表示倒数
console.log([1, 2, 3, 4].copyWithin(-2, 0)); // [1, 2, 1, 2]
 
console.log([1, 2, ,4].copyWithin(0, 2, 4)); // [, 4, , 4]
```

##### 3.2.4.6 遍历

###### 3.2.4.6.1 entries()

遍历键值对。

```javascript
for(let [key, value] of ['a', 'b'].entries()){
    console.log(key, value);
}
// 0 "a"
// 1 "b"
 
// 不使用 for... of 循环
let entries = ['a', 'b'].entries();
console.log(entries.next().value); // [0, "a"]
console.log(entries.next().value); // [1, "b"]
 
// 数组含空位
console.log([...[,'a'].entries()]); // [[0, undefined], [1, "a"]]
```

###### 3.2.4.6.2 keys()

遍历键名。

```javascript
for(let key of ['a', 'b'].keys()){
    console.log(key);
}
// 0
// 1
 
// 数组含空位
console.log([...[,'a'].keys()]); // [0, 1]
```

###### 3.2.4.6.3 values()

遍历键值。

```javascript
for(let value of ['a', 'b'].values()){
    console.log(value);
}
// "a"
// "b"
 
// 数组含空位
console.log([...[,'a'].values()]); // [undefined, "a"]
```

##### 3.2.4.7 包含

###### 3.2.4.7.1 includes()

数组是否包含指定值。

注意：与 Set 和 Map 的 has 方法区分；Set 的 has 方法用于查找值；Map 的 has 方法用于查找键名。

```javascript
// 参数1：包含的指定值
[1, 2, 3].includes(1);    // true
 
// 参数2：可选，搜索的起始索引，默认为0
[1, 2, 3].includes(1, 2); // false
 
// NaN 的包含判断
[1, NaN, 3].includes(NaN); // true
```

##### 3.2.4.8 嵌套数组转一维数组

###### 3.2.4.8.1 flat()

```javascript
console.log([1 ,[2, 3]].flat()); // [1, 2, 3]
 
// 指定转换的嵌套层数
console.log([1, [2, [3, [4, 5]]]].flat(2)); // [1, 2, 3, [4, 5]]
 
// 不管嵌套多少层
console.log([1, [2, [3, [4, 5]]]].flat(Infinity)); // [1, 2, 3, 4, 5]
 
// 自动跳过空位
console.log([1, [2, , 3]].flat());<p> // [1, 2, 3]
```

###### 3.2.4.8.2 flatMap()

先对数组中每个元素进行了的处理，再对数组执行 flat() 方法。

```javascript
// 参数1：遍历函数，该遍历函数可接受3个参数：当前元素、当前元素索引、原数组
// 参数2：指定遍历函数中 this 的指向
console.log([1, 2, 3].flatMap(n => [n * 2])); // [2, 4, 6]
```

##### 3.2.4.9 数组缓冲区

数组缓冲区是内存中的一段地址。

定型数组的基础。

实际字节数在创建时确定，之后只可修改其中的数据，不可修改大小。

###### 3.2.4.9.1 创建数组缓冲区

通过构造函数创建：

```javascript
let buffer = new ArrayBuffer(10);
console.log(buffer.byteLength); // 10
分割已有数组缓冲区
let buffer = new ArrayBuffer(10);
let buffer1 = buffer.slice(1, 3);
console.log(buffer1.byteLength); // 2
```

###### 3.2.4.9.2 视图

视图是用来操作内存的接口。

视图可以操作数组缓冲区或缓冲区字节的子集,并按照其中一种数值数据类型来读取和写入数据。

DataView 类型是一种通用的数组缓冲区视图,其支持所有8种数值型数据类型。

创建：

```javascript
// 默认 DataView 可操作数组缓冲区全部内容
let buffer = new ArrayBuffer(10),
    dataView = new DataView(buffer); 
dataView.setInt8(0,1);
console.log(dataView.getInt8(0)); // 1
 
// 通过设定偏移量(参数2)与长度(参数3)指定 DataView 可操作的字节范围
let buffer1 = new ArrayBuffer(10),
    dataView1 = new DataView(buffer1, 0, 3);
dataView1.setInt8(5,1); // RangeError
```

##### 3.2.4.10 定型数组

数组缓冲区的特定类型的视图。

可以强制使用特定的数据类型，而不是使用通用的 DataView 对象来操作数组缓冲区。

###### 3.2.4.10.1 创建

通过数组缓冲区生成

```javascript
let buffer = new ArrayBuffer(10),
    view = new Int8Array(buffer);
console.log(view.byteLength); // 10
```

通过构造函数

```javascript
let view = new Int32Array(10);
console.log(view.byteLength); // 40
console.log(view.length);     // 10
 
// 不传参则默认长度为0
// 在这种情况下数组缓冲区分配不到空间，创建的定型数组不能用来保存数据
let view1 = new Int32Array();
console.log(view1.byteLength); // 0
console.log(view1.length);     // 0
 
// 可接受参数包括定型数组、可迭代对象、数组、类数组对象
let arr = Array.from({
  0: '1',
  1: '2',
  2: 3,
  length: 3
});
let view2 = new Int16Array([1, 2]),
    view3 = new Int32Array(view2),
    view4 = new Int16Array(new Set([1, 2, 3])),
    view5 = new Int16Array([1, 2, 3]),
    view6 = new Int16Array(arr);
console.log(view2 .buffer === view3.buffer); // false
console.log(view4.byteLength); // 6
console.log(view5.byteLength); // 6
console.log(view6.byteLength); // 6
```

###### 3.2.4.10.2 注意要点

length 属性不可写，如果尝试修改这个值，在非严格模式下会直接忽略该操作，在严格模式下会抛出错误。

```javascript
let view = new Int16Array([1, 2]);
view.length = 3;
console.log(view.length); // 2
```

定型数组可使用 entries()、keys()、values()进行迭代。

```javascript
let view = new Int16Array([1, 2]);
for(let [k, v] of view.entries()){
    console.log(k, v);
}
// 0 1
// 1 2
```

- find() 等方法也可用于定型数组，但是定型数组中的方法会额外检查数值类型是否安全,也会通过 Symbol.species 确认方法的返回值是定型数组而非普通数组。
- concat() 方法由于两个定型数组合并结果不确定，故不能用于定型数组；
- 另外，由于定型数组的尺寸不可更改,可以改变数组的尺寸的方法，例如 splice() ，不适用于定型数组。

```javascript
let view = new Int16Array([1, 2]);
view.find((n) > 1); // 2
```

所有定型数组都含有静态 of() 方法和 from() 方法,运行效果分别与 Array.of() 方法和 Array.from() 方法相似，区别是定型数组的方法返回定型数组，而普通数组的方法返回普通数组。

```javascript
let view = Int16Array.of(1, 2);
console.log(view instanceof Int16Array); // true
```

定型数组不是普通数组，不继承自 Array 。

```javascript
let view = new Int16Array([1, 2]);
console.log(Array.isArray(view)); // false
```

定型数组中增加了 set() 与 subarray() 方法。 set() 方法用于将其他数组复制到已有定型数组, subarray() 用于提取已有定型数组的一部分形成新的定型数组。

```javascript
// set 方法
// 参数1：一个定型数组或普通数组
// 参数2：可选，偏移量，开始插入数据的位置，默认为0
let view= new Int16Array(4);
view.set([1, 2]);
view.set([3, 4], 2);
console.log(view); // [1, 2, 3, 4]
 
// subarray 方法
// 参数1：可选，开始位置
// 参数2：可选，结束位置(不包含结束位置)
let view= new Int16Array([1, 2, 3, 4]), 
    subview1 = view.subarray(), 
    subview2 = view.subarray(1), 
    subview3 = view.subarray(1, 3);
console.log(subview1); // [1, 2, 3, 4]
console.log(subview2); // [2, 3, 4]
console.log(subview3); // [2, 3]
```

##### 3.2.4.11 扩展运算符

复制数组

```javascript
let arr = [1, 2],
    arr1 = [...arr];
console.log(arr1); // [1, 2]
 
// 数组含空位
let arr2 = [1, , 3],
    arr3 = [...arr2];
console.log(arr3); [1, undefined, 3]
```

合并数组

```javascript
console.log([...[1, 2],...[3, 4]]); // [1, 2, 3, 4]
```

## 4. 运算符和语句

### 4.1 ES6 函数

#### 4.1.1 函数参数的扩展

##### 4.1.1.1 默认参数

基本用法

```javascript
function fn(name,age=17){
 console.log(name+","+age);
}
fn("Amy",18);  // Amy,18
fn("Amy","");  // Amy,
fn("Amy");     // Amy,17
```

注意点：使用函数默认参数时，不允许有同名参数。

```javascript
// 不报错
function fn(name,name){
 console.log(name);
}
 
// 报错
//SyntaxError: Duplicate parameter name not allowed in this context
function fn(name,name,age=17){
 console.log(name+","+age);
}
```

只有在未传递参数，或者参数为 undefined 时，才会使用默认参数，null 值被认为是有效的值传递。

```javascript
function fn(name,age=17){
    console.log(name+","+age);
}
fn("Amy",null); // Amy,null
```

函数参数默认值存在暂时性死区，在函数参数默认值表达式中，还未初始化赋值的参数值无法作为其他参数的默认值。

```javascript
function f(x,y=x){
    console.log(x,y);
}
f(1);  // 1 1
 
function f(x=y){
    console.log(x);
}
f();  // ReferenceError: y is not defined
```

##### 4.1.1.2 不定参数

不定参数用来表示不确定参数个数，形如，...变量名，由...加上一个具名参数标识符组成。具名参数只能放在参数组的最后，并且有且只有一个不定参数。

基本用法

```javascript
function f(...values){
    console.log(values.length);
}
f(1,2);      //2
f(1,2,3,4);  //4
```

#### 4.1.2 箭头函数

箭头函数提供了一种更加简洁的函数书写方式。基本语法是：

```
参数 => 函数体
```

基本用法：

```javascript
var f = v => v;
//等价于
var f = function(a){
 return a;
}
f(1);  //1
```

当箭头函数没有参数或者有多个参数，要用 **()** 括起来。

```javascript
var f = (a,b) => a+b;
f(6,2);  //8
```

当箭头函数函数体有多行语句，用 **{}** 包裹起来，表示代码块，当只有一行语句，并且需要返回结果时，可以省略 **{}** , 结果会自动返回。

```javascript
var f = (a,b) => {
 let result = a+b;
 return result;
}
f(6,2);  // 8
```

当箭头函数要返回对象的时候，为了区分于代码块，要用 **()** 将对象包裹起来

```javascript
// 报错
var f = (id,name) => {id: id, name: name};
f(6,2);  // SyntaxError: Unexpected token :
 
// 不报错
var f = (id,name) => ({id: id, name: name});
f(6,2);  // {id: 6, name: 2}
```

注意点：没有 this、super、arguments 和 new.target 绑定。

```javascript
var func = () => {
  // 箭头函数里面没有 this 对象，
  // 此时的 this 是外层的 this 对象，即 Window 
  console.log(this)
}
func(55)  // Window 
 
var func = () => {    
  console.log(arguments)
}
func(55);  // ReferenceError: arguments is not defined
```

箭头函数体中的 this 对象，是定义函数时的对象，而不是使用函数时的对象。

```javascript
function fn(){
  setTimeout(()=>{
    // 定义时，this 绑定的是 fn 中的 this 对象
    console.log(this.a);
  },0)
}
var a = 20;
// fn 的 this 对象为 {a: 18}
fn.call({a: 18});  // 18
```

不可以作为构造函数，也就是不能使用 new 命令，否则会报错

##### 4.1.2.1 适合使用的场景

ES6 之前，JavaScript 的 this 对象一直很令人头大，回调函数，经常看到 var self = this 这样的代码，为了将外部 this 传递到回调函数中，那么有了箭头函数，就不需要这样做了，直接使用 this 就行。

```javascript
// 回调函数
var Person = {
    'age': 18,
    'sayHello': function () {
      setTimeout(function () {
        console.log(this.age);
      });
    }
};
var age = 20;
Person.sayHello();  // 20
 
var Person1 = {
    'age': 18,
    'sayHello': function () {
      setTimeout(()=>{
        console.log(this.age);
      });
    }
};
var age = 20;
Person1.sayHello();  // 18
```

所以，当我们需要维护一个 this 上下文的时候，就可以使用箭头函数。

##### 4.1.2.2 不适合使用的场景

定义函数的方法，且该方法中包含 this

```javascript
var Person = {
    'age': 18,
    'sayHello': ()=>{
        console.log(this.age);
      }
};
var age = 20;
Person.sayHello();  // 20
// 此时 this 指向的是全局对象
 
var Person1 = {
    'age': 18,
    'sayHello': function () {
        console.log(this.age);
    }
};
var age = 20;
Person1.sayHello();   // 18
// 此时的 this 指向 Person1 对象
```

需要动态 this 的时候

```javascript
var button = document.getElementById('userClick');
button.addEventListener('click', () => {
     this.classList.toggle('on');
});
```

button 的监听函数是箭头函数，所以监听函数里面的 this 指向的是定义的时候外层的 this 对象，即 Window，导致无法操作到被点击的按钮对象。

### 4.3 ES6 Class 类

在ES6中，class (类)作为对象的模板被引入，可以通过 class 关键字定义类。

class 的本质是 function。

它可以看作一个语法糖，让对象原型的写法更加清晰、更像面向对象编程的语法。

#### 4.3.1 基础用法

##### 4.3.1.1 类定义

类表达式可以为匿名或命名。

```javascript
// 匿名类
let Example = class {
    constructor(a) {
        this.a = a;
    }
}
// 命名类
let Example = class Example {
    constructor(a) {
        this.a = a;
    }
}
```

##### 4.3.1.2 类声明

```javascript
class Example {
    constructor(a) {
        this.a = a;
    }
}
```

注意要点：不可重复声明。

```javascript
class Example{}
class Example{}
// Uncaught SyntaxError: Identifier 'Example' has already been declared
 
let Example1 = class{}
class Example{}
// Uncaught SyntaxError: Identifier 'Example' has already been declared
```

类定义不会被提升，这意味着，必须在访问前对类进行定义，否则就会报错。

类中方法不需要 function 关键字。

方法间不能加分号。

```javascript
new Example(); 
class Example {}
```

##### 4.3.1.3 类的主体

###### 4.3.1.3.1 属性 

**prototype**

ES6 中，prototype 仍旧存在，虽然可以直接自类中定义方法，但是其实方法还是定义在 prototype 上的。 覆盖方法 / 初始化时添加方法

```javascript
Example.prototype={
    //methods
}
```

**添加方法**

```javascript
Object.assign(Example.prototype,{
    //methods
})
```

**静态属性**

静态属性：class 本身的属性，即直接定义在类内部的属性（ Class.propname ），不需要实例化。 ES6 中规定，Class 内部只有静态方法，没有静态属性。

```javascript
class Example {
// 新提案
    static a = 2;
}
// 目前可行写法
Example.b = 2;
```

**公共属性**

```javascript
class Example{}
Example.prototype.a = 2;
```

**实例属性**

实例属性：定义在实例对象（ this ）上的属性。

```javascript
class Example {
    a = 2;
    constructor () {
        console.log(this.a);
    }
}
```

**name 属性**

返回跟在 class 后的类名(存在时)。

```javascript
let Example=class Exam {
    constructor(a) {
        this.a = a;
    }
}
console.log(Example.name); // Exam
 
let Example=class {
    constructor(a) {
        this.a = a;
    }
}
console.log(Example.name); // Example
```

###### 4.3.1.3.2 方法

**constructor 方法**

constructor 方法是类的默认方法，创建类的实例化对象时被调用。

```javascript
class Example{
    constructor(){
      console.log('我是constructor');
    }
}
new Example(); // 我是constructor
```

**返回对象**

```javascript
class Test {
    constructor(){
        // 默认返回实例对象 this
    }
}
console.log(new Test() instanceof Test); // true
 
class Example {
    constructor(){
        // 指定返回对象
        return new Test();
    }
}
console.log(new Example() instanceof Example); // false
```

**静态方法**

```javascript
class Example{
    static sum(a, b) {
        console.log(a+b);
    }
}
Example.sum(1, 2); // 3
```

**原型方法**

```javascript
class Example {
    sum(a, b) {
        console.log(a + b);
    }
}
let exam = new Example();
exam.sum(1, 2); // 3
```

**实例方法**

```javascript
class Example {
    constructor() {
        this.sum = (a, b) => {
            console.log(a + b);
        }
    }
}
```

###### 4.3.1.3.3 类的实例化

**new**

class 的实例化必须通过 new 关键字。

```javascript
class Example {}
 
let exam1 = Example(); 
// Class constructor Example cannot be invoked without 'new'
```

**实例化对象**

共享原型对象

```javascript
class Example {
    constructor(a, b) {
        this.a = a;
        this.b = b;
        console.log('Example');
    }
    sum() {
        return this.a + this.b;
    }
}
let exam1 = new Example(2, 1);
let exam2 = new Example(3, 1);
 
// __proto__ 已废弃，不建议使用
// console.log(exam1.__proto__ == exam2.__proto__); 
 
console.log(Object.getPrototypeOf(exam1) === Object.getPrototypeOf(exam2));// true
 
Object.getPrototypeOf(exam1).sub = function() {
    return this.a - this.b;
}
console.log(exam1.sub()); // 1
console.log(exam2.sub()); // 2
```

#### 4.3.2 decorator

decorator 是一个函数，用来修改类的行为，在代码编译时产生作用。

##### 4.3.2.1 类修饰

**一个参数**

第一个参数 target，指向类本身。

```javascript
function testable(target) {
    target.isTestable = true;
}
@testable
class Example {}
Example.isTestable; // true
```

**多个参数**

嵌套实现

```javascript
function testable(isTestable) {
    return function(target) {
        target.isTestable=isTestable;
    }
}
@testable(true)
class Example {}
Example.isTestable; // true
```

**实例属性**

上面两个例子添加的是静态属性，若要添加实例属性，在类的 prototype 上操作即可。

##### 4.3.2.2 方法修饰

3个参数：target（类的原型对象）、name（修饰的属性名）、descriptor（该属性的描述对象）。

```javascript
class Example {
    @writable
    sum(a, b) {
        return a + b;
    }
}
function writable(target, name, descriptor) {
    descriptor.writable = false;
    return descriptor; // 必须返回
}
```

**修饰器执行顺序**

由外向内进入，由内向外执行。

```javascript
class Example {
    @logMethod(1)
    @logMethod(2)
    sum(a, b){
        return a + b;
    }
}
function logMethod(id) {
    console.log('evaluated logMethod'+id);
    return (target, name, desctiptor) => console.log('excuted         logMethod '+id);
}
// evaluated logMethod 1
// evaluated logMethod 2
// excuted logMethod 2
// excuted logMethod 1
```

#### 4.3.3 封装与继承

##### 4.3.3.1 getter / setter

```javascript
class Example{
    constructor(a, b) {
        this.a = a; // 实例化时调用 set 方法
        this.b = b;
    }
    get a(){
        console.log('getter');
        return this.a;
    }
    set a(a){
        console.log('setter');
        this.a = a; // 自身递归调用
    }
}
let exam = new Example(1,2); // 不断输出 setter ，最终导致 RangeError
class Example1{
    constructor(a, b) {
        this.a = a;
        this.b = b;
    }
    get a(){
        console.log('getter');
        return this._a;
    }
    set a(a){
        console.log('setter');
        this._a = a;
    }
}
let exam1 = new Example1(1,2); // 只输出 setter , 不会调用 getter 方法
console.log(exam1._a); // 1, 可以直接访问
```

getter 不可单独出现

```javascript
class Example {
    constructor(a) {
        this.a = a; 
    }
    get a() {
        return this.a;
    }
}
let exam = new Example(1); // Uncaught TypeError: Cannot set property // a of #<Example> which has only a getter
```

getter 与 setter 必须同级出现

```javascript
class Father {
    constructor(){}
    get a() {
        return this._a;
    }
}
class Child extends Father {
    constructor(){
        super();
    }
    set a(a) {
        this._a = a;
    }
}
let test = new Child();
test.a = 2;
console.log(test.a); // undefined
 
class Father1 {
    constructor(){}
    // 或者都放在子类中
    get a() {
        return this._a;
    }
    set a(a) {
        this._a = a;
    }
}
class Child1 extends Father1 {
    constructor(){
        super();
    }
}
let test1 = new Child1();
test1.a = 2;
console.log(test1.a); // 2
```

##### 4.3.3.2 extends

通过 extends 实现类的继承。

```javascript
class Child extends Father { ... }
```

##### 4.3.3.3 super

子类 constructor 方法中必须有 super ，且必须出现在 this 之前。

```javascript
class Father {
    constructor() {}
}
class Child extends Father {
    constructor() {}
    // or 
    // constructor(a) {
        // this.a = a;
        // super();
    // }
}
let test = new Child(); // Uncaught ReferenceError: Must call super 
// constructor in derived class before accessing 'this' or returning 
// from derived constructor
```

调用父类构造函数,只能出现在子类的构造函数。

```javascript
class Father {
    test(){
        return 0;
    }
    static test1(){
        return 1;
    }
}
class Child extends Father {
    constructor(){
        super();
    }
}
class Child1 extends Father {
    test2() {
        super(); // Uncaught SyntaxError: 'super' keyword unexpected     
        // here
    }
}
```

调用父类方法, super 作为对象，在普通方法中，指向父类的原型对象，在静态方法中，指向父类

```javascript
class Child2 extends Father {
    constructor(){
        super();
        // 调用父类普通方法
        console.log(super.test()); // 0
    }
    static test3(){
        // 调用父类静态方法
        return super.test1+2;
    }
}
Child2.test3(); // 3
```

不可继承常规对象。

```javascript
var Father = {
    // ...
}
class Child extends Father {
     // ...
}
// Uncaught TypeError: Class extends value #<Object> is not a constructor or null
 
// 解决方案
Object.setPrototypeOf(Child.prototype, Father);
```

### 4.4  ES6 模块

在 ES6 前， 实现模块化使用的是 RequireJS 或者 seaJS（分别是基于 AMD 规范的模块化库， 和基于 CMD 规范的模块化库）。

ES6 引入了模块化，其设计思想是在编译时就能确定模块的依赖关系，以及输入和输出的变量。

ES6 的模块化分为导出（export） @与导入（import）两个模块。

#### 4.4.1 export 与 import

##### 4.4.1.1 基本用法

模块导入导出各种类型的变量，如字符串，数值，函数，类。

- 导出的函数声明与类声明必须要有名称（export default 命令另外考虑）。 
- 不仅能导出声明还能导出引用（例如函数）。
- export 命令可以出现在模块的任何位置，但必需处于模块顶层。
- import 命令会提升到整个模块的头部，首先执行。

```javascript
/*-----export [test.js]-----*/
let myName = "Tom";
let myAge = 20;
let myfn = function(){
    return "My name is" + myName + "! I'm '" + myAge + "years old."
}
let myClass =  class myClass {
    static a = "yeah!";
}
export { myName, myAge, myfn, myClass }
 
/*-----import [xxx.js]-----*/
import { myName, myAge, myfn, myClass } from "./test.js";
console.log(myfn());// My name is Tom! I'm 20 years old.
console.log(myAge);// 20
console.log(myName);// Tom
console.log(myClass.a );// yeah!
```

建议使用大括号指定所要输出的一组变量写在文档尾部，明确导出的接口。

函数与类都需要有对应的名称，导出文档尾部也避免了无对应名称。

##### 4.4.1.2 as 的用法

export 命令导出的接口名称，须和模块内部的变量有一一对应关系。

导入的变量名，须和导出的接口名称相同，即顺序可以不一致。

```javascript
/*-----export [test.js]-----*/
let myName = "Tom";
export { myName as exportName }
 
/*-----import [xxx.js]-----*/
import { exportName } from "./test.js";
console.log(exportName);// Tom
使用 as 重新定义导出的接口名称，隐藏模块内部的变量
/*-----export [test1.js]-----*/
let myName = "Tom";
export { myName }
/*-----export [test2.js]-----*/
let myName = "Jerry";
export { myName }
/*-----import [xxx.js]-----*/
import { myName as name1 } from "./test1.js";
import { myName as name2 } from "./test2.js";
console.log(name1);// Tom
console.log(name2);// Jerry
```

不同模块导出接口名称命名重复， 使用 as 重新定义变量名。

##### 4.4.1.3 import 命令的特点

**只读属性**：不允许在加载模块的脚本里面，改写接口的引用指向，即可以改写 import 变量类型为对象的属性值，不能改写 import 变量类型为基本类型的值。

```javascript
import {a} from "./xxx.js"
a = {}; // error
 
import {a} from "./xxx.js"
a.foo = "hello"; // a = { foo : 'hello' }
```

**单例模式**：多次重复执行同一句 import 语句，那么只会执行一次，而不会执行多次。import 同一模块，声明不同接口引用，会声明对应变量，但只执行一次 import 。

```javascript
import { a } "./xxx.js";
import { a } "./xxx.js";
// 相当于 import { a } "./xxx.js";
 
import { a } from "./xxx.js";
import { b } from "./xxx.js";
// 相当于 import { a, b } from "./xxx.js";
```

静态执行特性：import 是静态执行，所以不能使用表达式和变量。

```javascript
import { "f" + "oo" } from "methods";
// error
let module = "methods";
import { foo } from module;
// error
if (true) {
  import { foo } from "method1";
} else {
  import { foo } from "method2";
}
// error
```

##### 4.4.1.4 export default 命令

- 在一个文件或模块中，export、import 可以有多个，export default 仅有一个。
- export default 中的 default 是对应的导出接口变量。
- 通过 export 方式导出，在导入时要加{ }，export default 则不需要。
- export default 向外暴露的成员，可以使用任意变量来接收。

```javascript
var a = "My name is Tom!";
export default a; // 仅有一个
export default var c = "error"; 
// error，default 已经是对应的导出变量，不能跟着变量声明语句
 
import b from "./xxx.js"; // 不需要加{}， 使用任意变量接收
```

#### 4.4.2 复合使用

> **注**：import() 是提案，这边暂时不延伸讲解。

export 与 import 可以在同一模块使用，使用特点：

- 可以将导出接口改名，包括 default。
- 复合使用 export 与 import ，也可以导出全部，当前模块导出的接口会覆盖继承导出的。

```javascript
export { foo, bar } from "methods";
 
// 约等于下面两段语句，不过上面导入导出方式该模块没有导入 foo 与 bar
import { foo, bar } from "methods";
export { foo, bar };
 
/* ------- 特点 1 --------*/
// 普通改名
export { foo as bar } from "methods";
// 将 foo 转导成 default
export { foo as default } from "methods";
// 将 default 转导成 foo
export { default as foo } from "methods";
 
/* ------- 特点 2 --------*/
export * from "methods";
```

## 5. 异步编程

### 5.1 ES6 Promise 对象

是异步编程的一种解决方案。

从语法上说，Promise 是一个对象，从它可以获取异步操作的消息。

#### 5.1.1 Promise 状态

##### 5.1.1.1 状态的特点

Promise 异步操作有三种状态：pending（进行中）、fulfilled（已成功）和 rejected（已失败）。除了异步操作的结果，任何其他操作都无法改变这个状态。

Promise 对象只有：从 pending 变为 fulfilled 和从 pending 变为 rejected 的状态改变。只要处于 fulfilled 和 rejected ，状态就不会再变了即 resolved（已定型）。

```javascript
const p1 = new Promise(function(resolve,reject){
    resolve('success1');
    resolve('success2');
}); 
const p2 = new Promise(function(resolve,reject){  
    resolve('success3'); 
    reject('reject');
});
p1.then(function(value){  
    console.log(value); // success1
});
p2.then(function(value){ 
    console.log(value); // success3
});
```

##### 5.1.1.2 状态的缺点

无法取消 Promise ，一旦新建它就会立即执行，无法中途取消。

如果不设置回调函数，Promise 内部抛出的错误，不会反应到外部。

当处于 pending 状态时，无法得知目前进展到哪一个阶段（刚刚开始还是即将完成）。

#### 5.1.2 then 方法

then 方法接收两个函数作为参数，第一个参数是 Promise 执行成功时的回调，第二个参数是 Promise 执行失败时的回调，两个函数只会有一个被调用。

##### 5.1.2.1 then 方法的特点

在 JavaScript 事件队列的当前运行完成之前，回调函数永远不会被调用。

```javascript
const p = new Promise(function(resolve,reject){
  resolve('success');
});
 
p.then(function(value){
  console.log(value);
});
 
console.log('first');
// first
// success
```

通过 **.then** 形式添加的回调函数，不论什么时候，都会被调用。

通过多次调用

```javascript
.then
```

，可以添加多个回调函数，它们会按照插入顺序并且独立运行。

```javascript
const p = new Promise(function(resolve,reject){
  resolve(1);
}).then(function(value){ // 第一个then // 1
  console.log(value);
  return value * 2;
}).then(function(value){ // 第二个then // 2
  console.log(value);
}).then(function(value){ // 第三个then // undefined
  console.log(value);
  return Promise.resolve('resolve'); 
}).then(function(value){ // 第四个then // resolve
  console.log(value);
  return Promise.reject('reject'); 
}).then(function(value){ // 第五个then //reject:reject
  console.log('resolve:' + value);
}, function(err) {
  console.log('reject:' + err);
});
```

then 方法将返回一个 resolved 或 rejected 状态的 Promise 对象用于链式调用，且 Promise 对象的值就是这个返回值。

##### 5.1.2.2 then 方法注意点

简便的 Promise 链式编程最好保持扁平化，不要嵌套 Promise。

注意总是返回或终止 Promise 链。

```javascript
const p1 = new Promise(function(resolve,reject){
  resolve(1);
}).then(function(result) {
  p2(result).then(newResult => p3(newResult));
}).then(() => p4());
```

创建新 Promise 但忘记返回它时，对应链条被打破，导致 p4 会与 p2 和 p3 同时进行。

大多数浏览器中不能终止的 Promise 链里的 rejection，建议后面都跟上 **.catch(error => console.log(error));**

### 5.2 ES6 Generator 函数

ES6 新引入了 Generator 函数，可以通过 yield 关键字，把函数的执行流挂起，为改变执行流程提供了可能，从而为异步编程提供解决方案。 基本用法

#### 5.2.1 Generator 函数组成

Generator 有两个区分于普通函数的部分：

- 一是在 function 后面，函数名之前有个 * ；
- 函数内部有 yield 表达式。

其中 * 用来表示函数为 Generator 函数，yield 用来定义函数内部的状态。

```javascript
function* func(){
 console.log("one");
 yield '1';
 console.log("two");
 yield '2'; 
 console.log("three");
 return '3';
}
```

#### 5.2.2 执行机制

调用 Generator 函数和调用普通函数一样，在函数名后面加上()即可，但是 Generator 函数不会像普通函数一样立即执行，而是返回一个指向内部状态对象的指针，所以要调用遍历器对象Iterator 的 next 方法，指针就会从函数头部或者上一次停下来的地方开始执行。

```javascript
f.next();
// one
// {value: "1", done: false}
 
f.next();
// two
// {value: "2", done: false}
 
f.next();
// three
// {value: "3", done: true}
 
f.next();
// {value: undefined, done: true}
```

第一次调用 next 方法时，从 Generator 函数的头部开始执行，先是打印了 one ,执行到 yield 就停下来，并将yield 后边表达式的值 '1'，作为返回对象的 value 属性值，此时函数还没有执行完， 返回对象的 done 属性值是 false。

第二次调用 next 方法时，同上步 。

第三次调用 next 方法时，先是打印了 three ，然后执行了函数的返回操作，并将 return 后面的表达式的值，作为返回对象的 value 属性值，此时函数已经结束，多以 done 属性值为true 。

第四次调用 next 方法时， 此时函数已经执行完了，所以返回 value 属性值是 undefined ，done 属性值是 true 。如果执行第三步时，没有 return 语句的话，就直接返回 {value: undefined, done: true}。

#### 5.2.3 函数返回的遍历器对象的方法

##### 5.2.3.1 **next 方法**

一般情况下，next 方法不传入参数的时候，yield 表达式的返回值是 undefined 。当 next 传入参数的时候，该参数会作为上一步yield的返回值。

```javascript
function* sendParameter(){
    console.log("start");
    var x = yield '2';
    console.log("one:" + x);
    var y = yield '3';
    console.log("two:" + y);
    console.log("total:" + (x + y));
}
```

next不传参

```javascript
var sendp1 = sendParameter();
sendp1.next();
// start
// {value: "2", done: false}
sendp1.next();
// one:undefined
// {value: "3", done: false}
sendp1.next();
// two:undefined
// total:NaN
// {value: undefined, done: true}
next传参
var sendp2 = sendParameter();
sendp2.next(10);
// start
// {value: "2", done: false}
sendp2.next(20);
// one:20
// {value: "3", done: false}
sendp2.next(30);
// two:30
// total:50
// {value: undefined, done: true}
```

除了使用 next ，还可以使用 for... of 循环遍历 Generator 函数生产的 Iterator 对象。

##### 5.2.3.2 **return 方法**

return 方法返回给定值，并结束遍历 Generator 函数。

return 方法提供参数时，返回该参数；不提供参数时，返回 undefined 。

```javascript
function* foo(){
    yield 1;
    yield 2;
    yield 3;
}
var f = foo();
f.next();
// {value: 1, done: false}
f.return("foo");
// {value: "foo", done: true}
f.next();
// {value: undefined, done: true}
throw 方法
throw 方法可以再 Generator 函数体外面抛出异常，再函数体内部捕获。
var g = function* () {
  try {
    yield;
  } catch (e) {
    console.log('catch inner', e);
  }
};
 
var i = g();
i.next();
 
try {
  i.throw('a');
  i.throw('b');
} catch (e) {
  console.log('catch outside', e);
}
// catch inner a
// catch outside b
```

遍历器对象抛出了两个错误，第一个被 Generator 函数内部捕获，第二个因为函数体内部的catch 函数已经执行过了，不会再捕获这个错误，所以这个错误就抛出 Generator 函数体，被函数体外的 catch 捕获。

##### 5.2.3.3 **yield\* 表达式**

yield* 表达式表示 yield 返回一个遍历器对象，用于在 Generator 函数内部，调用另一个 Generator 函数。

```javascript
function* callee() {
    console.log('callee: ' + (yield));
}
function* caller() {
    while (true) {
        yield* callee();
    }
}
const callerObj = caller();
callerObj.next();
// {value: undefined, done: false}
callerObj.next("a");
// callee: a
// {value: undefined, done: false}
callerObj.next("b");
// callee: b
// {value: undefined, done: false}
 
// 等同于
function* caller() {
    while (true) {
        for (var value of callee) {
          yield value;
        }
    }
}
```

#### 5.2.4 使用场景

##### 5.2.4.1 **实现 Iterator**

为不具备 Iterator 接口的对象提供遍历方法。

```javascript
function* objectEntries(obj) {
    const propKeys = Reflect.ownKeys(obj);
    for (const propKey of propKeys) {
        yield [propKey, obj[propKey]];
    }
}
 
const jane = { first: 'Jane', last: 'Doe' };
for (const [key,value] of objectEntries(jane)) {
    console.log(`${key}: ${value}`);
}
// first: Jane
// last: Doe
```

Reflect.ownKeys() 返回对象所有的属性，不管属性是否可枚举，包括 Symbol。

jane 原生是不具备 Iterator 接口无法通过 for... of遍历。这边用了 Generator 函数加上了 Iterator 接口，所以就可以遍历 jane 对象了。
