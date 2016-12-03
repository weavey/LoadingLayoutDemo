# LoadingLayoutDemo
### 前言
项目里都会遇到几种页面，分别为加载中、无网络、无数据、出错四种情况，经常要使用，所以封成库引用了，方便使用，顺便分享出来。先看一下效果：

![](https://dn-mhke0kuv.qbox.me/341fb9f9a8edfbe56649.gif)

原理比较简单，继承FrameLayout，在xml渲染完成后，加上加载中、无网络、无数据、出错四个页面，根据需要控制显示哪一层，花了些时间，开了很多方法出来，支持很多属性的设置，算是比较实用，源码里已对各个方法的作用都加了注释，就不做过多解释了。
### 使用方式
gradle引用：
>compile 'com.lai.weavey:loadinglayout:1.2'

### 使用说明
>LoadingLayout支持全局配置，对所有使用到的地方都起效，需要在Application中配置，如下：

```
 public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LoadingLayout.getConfig()
                .setErrorText("出错啦~请稍后重试！")
                .setEmptyText("抱歉，暂无数据")
                .setNoNetworkText("无网络连接，请检查您的网络···")
                .setErrorImage(R.mipmap.define_error)
                .setEmptyImage(R.mipmap.define_empty)
                .setNoNetworkImage(R.mipmap.define_nonetwork)
                .setAllTipTextColor(R.color.gray)
                .setAllTipTextSize(14)
                .setReloadButtonText("点我重试哦")
                .setReloadButtonTextSize(14)
                .setReloadButtonTextColor(R.color.gray)
                .setReloadButtonWidthAndHeight(150,40);
    }
}
```

>由于“加载中”的页面，可能每个App都不一样，因此，LoadingLayout支持自定义LoadingPage，如下：

```
 LoadingLayout.getConfig()
     .setLoadingPageLayout(R.layout.define_loading_page);

```

>同时，为了适应个别界面的“特殊需求”，LoadingLayout也支持局部设置各种属性，仅对当前对象生效，不影响全局。如下：

```
        LoadingLayout  loading = (LoadingLayout) findViewById(R.id.loading_layout);
        loading.setLoadingPage(R.layout.define_loading_page)
                .setEmptyText("暂无报告数据")
                .setErrorText("")
                .setNoNetworkText("")
                .setErrorImage(R.mipmap.ic_launcher)
                .setErrorTextSize(16)
                .setReloadButtonText("点我重新加载哦"); //等等
                
```

>为ReloadButton设置监听:

```
loadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                
            }
        });
```

>设置显示的页面：

```
 loadingLayout.setStatus(LoadingLayout.Loading);//加载中
 loadingLayout.setStatus(LoadingLayout.Empty);//无数据
 loadingLayout.setStatus(LoadingLayout.Error);//错误
 loadingLayout.setStatus(LoadingLayout.No_Network);//无网络
 loadingLayout.setStatus(LoadingLayout.Success);//加载成功
```
>最后，在xml里面使用：

```
<com.weavey.loading.lib.LoadingLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    app:isFirstVisible="true">

    <TextView
        android:background="@color/colorPrimary"
        android:visibility="visible"
        android:gravity="center"
        android:textColor="@android:color/white"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="ContentView"/>

</com.weavey.loading.lib.LoadingLayout>
```
>注意：

（1）isFirstVisible属性用来控制contentView一开始是否隐藏，由于LoadingLayout原理是在xml渲染完成后在contentView上铺上三层View，因此，一开始如果不隐藏，等contentView渲染完成后调用: ```loadingLayout.setStatus(LoadingLayout.Loading);```
会造成界面闪烁的效果，影响体验，因此默认将contentView隐藏，所以数据加载完成后一定要调用```loadingLayout.setStatus(LoadingLayout.Success);```，将contentView显示出来。这样也能解决未获取到数据的情况下，被用户看到杂乱无章的布局，个人还是比较喜欢默认隐藏contentView；

（2）为了方便管理，LoadingLayout只能有一个直属子View，类似ScrollView，添加两个直属子View会抛出异常``` throw new IllegalStateException("LoadingLayout can host only one direct child");```；

（3）由于AS会直接将自定义View的特性反应在预览界面，所以在使用LoadingLayout的时候，会无法看到被LoadingLayout包裹住的布局（默认为gone），因此也可以将isFirstVisible属性暂时设为true，预览布局。
