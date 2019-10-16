# 单机点餐系统
### 首先介绍下这个系统，目前在单机上实现了点菜，菜价结算，用户注册，登录功能。
### 你想用这个系统来应用于生产环境是不现实的，这也是我用来学习Android的项目，所以项目里面有很多入门的demo代码。至于shit山命名这个很多是根老师代码敲的，为了避免一些麻烦，所以会有诸如getReCaiDishes这种命名。。。
### 至于注释，我很抱歉为了赶进度没有写，但比较简单的逻辑我相信我之后会能看懂的（并不）
### 更欢迎比我更新手的新手学习。
## 功能介绍
### 源码下的data/model下放了用到的四大模型
### Dish是菜盘；
### Dishes是菜盘里面的菜，因为这里没用到网络，所以这里使用源码存数据；
### DishMenu是点的菜的模型
### LoggedInUser是系统生成登录界面时自动生成，但我们并没有用到。
### DishDBHelper 操作点菜的数据库的辅助类
### DishListActivity是菜单的Activity
### DishMenuDBManager是操作菜单时用的类
### DishSetting是一个dialog模式的activity 用于菜单内的设置音乐播放状态
### Help是菜单的帮助界面的
### LoginActivity是登录界面的activity
### MainActivity是主入口的Activity但只有一个init
### MainTable是主界面，四方格，用于选择操作
### MusicPlayerService是一个后台播放音乐的服务我也不知道为啥老师表示想在点菜的时候听歌，主要是联系的service
### MyAdapter是一个继承于基础适配器的自定义适配器，用于菜单左侧的选择要展示的菜
### MyApplication是全局变量，存用户登录状态，UID
### MyFragment是一个自定义的碎片，用于各列表的替换的可复用fragment
### OrderedActivity是统计点菜的界面的Activity
### OrderOneDialog是点菜选择数量的自定义dialog
### SetUserActivity是修改用户名密码的Activity
### SignIn是注册的Activity
## 其他想起来再补充

