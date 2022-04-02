# java-finalwork-pro

# 技术栈分析

## rsa加密

## mybatis-plus

## mqtt&Emq

## jwt的token生成策略

## SpringBoot

## mysql & influxdb & redis

# 系统分析

## 1、用户登录及其权限控制

### 登录流程图

![image-20220316200515294](https://gitee.com/andyxiaopeng/picbed/raw/master/pic/image-20220316200515294.png)

#### 代码详解

1. 生成rsa公钥和私钥

   ````java
   @Override
       public Message getPublicKey() {
           Message<HashMap> message = new Message();
   
           HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
           objectObjectHashMap.put("hydrogen-Server",true);
   
           // publickey 需要用用 RSA生成
           RSAUtils rsaUtils = new RSAUtils();
           rsaUtils.iniRSA();
           objectObjectHashMap.put(rsaUtils.PUBLIC_KEY, rsaUtils.RSAkeyMap.get(rsaUtils.PUBLIC_KEY));
   
           message.setData(objectObjectHashMap);
           message.initSuccessMessage();
           return message;
       }
   ````

2. 密文解析且token生成

   ```java
      @Override
       public Message login(LoginForm loginForm) {
           Message<HashMap> message = new Message();
   
           // RSA解码
           String param = loginForm.getParam();
           RSAUtils rsaUtils = new RSAUtils();
           String s = rsaUtils.privateKeyDecrypt(param);
           JSONUtil jsonUtil = new JSONUtil();
           JSONObject jsonObject = jsonUtil.parseObj(s);
           String account = String.valueOf(jsonObject.get("username"));
           String password = String.valueOf(jsonObject.get("password"));
   
           // 使用账号与数据库对比验证
           QueryWrapper<User> qw = new QueryWrapper<>();
           qw.eq("account", account);
           List<User> users = userMapper.selectList(qw);
           if (users.size() == 0){
                message.initErrorMessage();
               return message;
           }
           for (User user : users) {
   
               // 验证密码是否正确
               boolean b = user.getPassword().equals(password);
               if (b) {
                   // Token 需要用java包生成
                   JWTUtils jwtUtils = new JWTUtils();
                   String token = jwtUtils.getToken(account,password);
   
                   // token 写入数据库
                   user.setAccesstoken(token);
                   userMapper.updateById(user);
   
                   // token返回前端
                   HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                   objectObjectHashMap.put("accessToken",token);
                   message.setData(objectObjectHashMap);
                   message.initSuccessMessage();
   
                   return message;
               }
   
           }
           return null;
       }
   ```

3. 权限获取

   ```java
   @Override
       public Message getUserInfo(LoginForm loginForm) {
           String accessToken = loginForm.getAccessToken();
   
           Message<HashMap> message = new Message();
           HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
           objectObjectHashMap.put("accessToken",accessToken);
   
           // 凭借token拿 用户的信息，包括权限
           // 根据token查询数据库
           QueryWrapper<User> qw = new QueryWrapper<>();
           qw.eq("accessToken", accessToken);
           List<User> users = userMapper.selectList(qw);
           if (users.size() == 0){
               return null;
           }
   
           for (User user : users) {
               objectObjectHashMap.put("username",user.getUsername());//用户名字
   
               ArrayList<String> permissions = new ArrayList<>();//用户权限
               permissions.add("admin");
               permissions.add("editor");
               objectObjectHashMap.put("permissions",permissions);
   
               ArrayList<String> avatar = new ArrayList<>();//用户头像链接
               avatar.add("https://i.gtimg.cn/club/item/face/img/2/15922_100.gif");
               avatar.add("https://i.gtimg.cn/club/item/face/img/8/15918_100.gif");
               objectObjectHashMap.put("avatar","https://baomidou.com/img/logo.svg");
   
           }
   
           message.setData(objectObjectHashMap);
   
           message.initSuccessMessage();
           return message;
       }
   ```

## 2、用户管理

1. 获取用户列表且实现分页数据查询，不仅加快后端查询能力且减轻前端数据渲染压力。同时还加了指定用户名的搜索功能

   ```java
   @Override
       public Message getList(UserManageForm userManageForm) {
   
           System.out.println(userManageForm);
           //获取前台发送过来的数据
           System.out.println(userManageForm);
           Integer pageNo = Integer.valueOf(userManageForm.getPageNo());
           Integer pageSize = Integer.valueOf(userManageForm.getPageSize());
           Page<User> page = new Page<>(pageNo, pageSize);
           Page<User> userPage;
   
           // 携带用户名的特定搜索
           String username = userManageForm.getUsername();
           if (username != null){
               QueryWrapper<User> wrapper = new QueryWrapper<>();
               User user = new User();
               user.setUsername(username);
               wrapper.setEntity(user);
               userPage = this.page(page, wrapper);
           }else {
               userPage = this.page(page, null);
           }
   
           Message message = new Message();
           message.setTotalCount(Math.toIntExact(userPage.getTotal()));
           List<User> records = userPage.getRecords();
           message.setData(records);
           message.initSuccessMessage();
           return message;
       }
   ```

2. 修改用户信息

   

3. 删除用户

   根据用户的id来进行删除操作

   ```java
   @Override
       public Message doDelete(doDeleteForm doDeleteForm) {
           String ids = doDeleteForm.getIds();
           String[] strings = ids.split(",");
           for (String s : strings) {
               System.out.println(s);
               userMapper.deleteById(s);
           }
           Message message = new Message();
           message.initSuccessMessage();
           return message;
       }
   ```

   

## 3、数据展示模块

从python模拟数据采集到后端处理再反馈前端的流程解析

![从python模拟数据采集到后端处理再反馈前端的流程图](E:\all_projection\javap\demo\README.assets\从python模拟数据采集到后端处理再反馈前端的流程图.png)



