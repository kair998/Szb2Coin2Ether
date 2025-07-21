# 1.1版本更新内容：
## 1，新增SpringBootWeb端程序
## 2，整合，规范代码。
### 2.1 新增“指定卡包”变量，便于管理多弹卡包的情况
### 代码中多以curBullet，bulletNumber指定第几弹卡包。这里讲一个我的问题，在toEther类中的转化方法的基础是card构造匹配，因此在类中代码要特别注意新增成员变量的赋值，我一开始在toEther中直接调用卡牌数量设定函数，忘记给toEther类的指定第几弹卡包的变量明确赋值，导致无论如何匹配不到，转化结果显示为0，实际上不追求美观（cardParser），两者皆不赋也能完成一种残缺的恰好匹配，也就是说，你要么二者都赋值，要么二者都不赋值都能出结果，但是一有一无就不行了。
<img width="2864" height="1642" alt="image" src="https://github.com/user-attachments/assets/38db5c2c-0af2-421a-9de5-503358b4ac46" />

# 实测环节：
## 第二弹卡包全卡满三10000金币≈8550以太
### 实际上这个数字相当接近第一弹卡包的转化效率，想必这也是策划精心设计的平衡
<img width="1369" height="247" alt="image" src="https://github.com/user-attachments/assets/dc4a3d31-c5ac-4200-b9d2-bced80513043">

👉 查看 [1.0 版本的 README](https://github.com/kair998/Szb2Coin2Ether/blob/main/README-HISTORY/README.md)




