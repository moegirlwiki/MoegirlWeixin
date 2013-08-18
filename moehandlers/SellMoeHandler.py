# coding: utf-8
'''
Created on 2013-7-3

@author: xuechong
'''
import Weixin
import random
import logging

sellMoeList=["自动应答姬卖萌中~\(≧▽≦)/~啦啦啦","人家还只会卖萌了啦 (＞﹏＜)","自动应答姬努力卖萌中(oﾟωﾟo)","自动应答姬各种卖萌中(＞。☆)"]
keyWordDict = {"么么哒":"欧尼酱么么哒\(≧ω≦)/","无路赛":"夏娜酱(＞﹏＜)!","给大爷笑一个":"讨厌了啦,就会欺负人家"}
helpmsg = "\n\n\n☆更新姬提示☆\n输入'帮助'可以查看现有功能哦"
class SellMoeHandler(object):
    @staticmethod
    def __helpkey__ ():
        return "卖萌"
    @staticmethod
    def __helpcontent__():
        return "只是卖萌了啦!"
    def handle(self,handlerChain):
        
        logging.debug(handlerChain.userMsg.get("MsgType"))
        
        if(handlerChain.getMsgType()!="text"):
            return None#if you cant handle this msg ,just return None or return handlerChain.invokeNext()
        if keyWordDict.has_key(handlerChain.getMsgContent()):
            return Weixin.textReply(handlerChain.userMsg,keyWordDict.get(handlerChain.getMsgContent()) + helpmsg)
        answer = random.randint(0,len(sellMoeList)-1)
        return Weixin.textReply(handlerChain.userMsg,sellMoeList[answer]+helpmsg)
    
    
    