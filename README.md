﻿# Szb2Coin2Ether

*《影之诗：超凡世界》金币兑换以太测试工具——Version 1.0*

## 全卡满三10000金币≈8500以太

![demo](demoImage.png)

## 项目结构：

### Card类：记录卡牌信息（Boolean），解析卡牌类型（CardParser），初始化构造卡牌（在toEther中构造键）。

### Card子类（NormalCard，silverBetterCard，mustRainbow）对应不同的CardMaker概率。

### Draw类：模拟抽卡（drawCard，drawAPack），记录抽卡信息，针对不同情况（必虹）选择不同抽卡方式（drawAMustRainbowPack，drawANormalPack），打印提示信息（cardStatResult，drawLogs）。

### toEther类：模拟抽卡之后的以太转化情况，根据构造模拟不同box情况下的以太转换效果，调用Draw对象进行抽卡并完成转换（card2Ether），统计抽卡中各类卡牌数量（getXCardTotal），计算转换收益（XToEther）。

## 存在问题：

### 不能处理box原有卡牌，实际上只模拟新抽到的卡牌分解。该问题似乎需要通过编程模拟全卡来解决

## 更新方向：

### 搭建Web端程序
