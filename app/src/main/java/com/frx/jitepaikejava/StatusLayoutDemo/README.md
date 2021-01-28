# StatusLayout  : 一个超高自定义度又简单的页面状态管理库
[https://github.com/Colaman0/StatusLayout](https://github.com/Colaman0/StatusLayout "原地址")
#### 业务场景需求：
##### 在日常开发App的过程中，我们少不了对`Activity`/`Fragment` 等做一些不同状态不同UI的状态管理逻辑，比如`空页面` `错误重试页面` 等等，网上也有很多作者写了开源库来处理这些问题
----
#### `StatusLayout`有以下几个优点

* ##### 自由定制需要的状态以及对应布局，只需要一行代码
* ##### 可以定制动画效果
* ##### 可以用在旧项目上，不需要修改原有xml文件
* ##### 可设置全局属性避免重复劳动

#### 效果图：

#### 具体使用步骤如下：

### 1.第一步先把`StatusLayout`作为根布局，这里有以下两种写法
* ##### 把`StatusLayout`作为根布局在activity/fragment/view 中使用 
  > 在xml内直接把`StatusLayout`作为根布局，注意`StatusLayout`内部子view数量不能超过1个，
                所以如果UI上需求需要排列多个View的时候，需要多套一层布局，比如：  

    ```
    <StatusLayout>
        <LinearLayout>    
            <View/>        
            <View/>
            <View/>
        </LinearLayout>
    </StatusLayout>
    
    ````     

*  #####  通过 `StatusLayout.init()`方法传入Context以及你要显示到的layout资源文件，这个方法会返回一个StatusLayout对象，所以大家可以在封装BaseActivity的时候这样写:  

    ```
    // 后续可以通过mStatusLayout添加不同状态对应的UI  
    StatusLayout mStatusLayout = StatusLayout.init(this, R.layout.activity_main);   
    setContentView(mStatusLayout);
    ```     
    
### 2. 添加不同状态对应的UI以及响应点击事件

* ##### `status` ： 作为一个状态布局的status标记，比如`空页面` `错误页面` 等等你想要添加进去的页面，设置一下自己想要添加的`status`
* ##### `layoutRes` : 对应上面的`status`， 一个`status`对应一个view，一个布局，比如上面`status`传入了一个empty，那我们这里对应可以添加一个空页面的layout资源文件id
* ##### `view` ： 跟`layoutRes`相似，考虑到有时候业务需求，某个状态下的页面可能按钮或者一些需要写的逻辑比较多比较复杂， 这个时候可以让开发者自己写一个view传进来，对应的一些逻辑判断则让`view`内部去处理 ,`StatusLayout`只负责切换
* ##### `clickRes` ：每一个布局，可以传递一个id进来，比如`错误重试页面` 可以传一个button的id进来，这样在button被点击的时候，可以通过回调来接收到点击事件 
    
### 3. 切换布局
####  通过`switchLayout()两种方法来切换布局  

* ##### `switchLayout`方法是用于切换你add进去的布局，只要传入你前面add布局的时候传入的status就可以了

### 5. 设置显示/隐藏的动画
##### 通过`setAnimation()` 来设置页面显示/隐藏的的动画, 也可以通过`setGlobalAnim()`来设置一个全局的动画效果，`setAnimation()`的优先级比`setGlobalAnim()`更高

### 6.设置全局属性
##### 考虑到APP里常见的`空页面` `loading` 之类的页面都是比较统一的，这个时候可以通过`StatusLayout.setGlobalData()`方法来设置全局的属性，这个时候可以设置全局属性来避免重复添加的代码，后续可以通过`add（）`方法来覆盖全局属性。
