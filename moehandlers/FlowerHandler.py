# coding: utf-8
'''
Created on 2013-7-4
花语handler
@author: xuechong
'''
import Weixin
import logging
from model.Flower import findByName

__flower_key__ = "花语"
class FlowerHandler(object):
   
    @staticmethod
    def __helpkey__ ():
        return __flower_key__
    @staticmethod
    def __helpcontent__():
        return "输入 **花语 或者 花语** 可以查看相应的花语哦"
    
    def handle(self,handlerChain):
        
        if (handlerChain.getMsgType()=="text"):
            content = handlerChain.getMsgContent()
            if (content.endswith(__flower_key__) or content.startswith(__flower_key__)):
                flowerName = content.replace(__flower_key__, "")
                if flowerName!="":
                    return Weixin.textReply(handlerChain.userMsg,findFlower(flowerName))
        
        return handlerChain.invokeNext()

def findFlower(flowerName):
    flowerList = findByName(flowerName)
    if flowerList==None or len(flowerList)<1:
        logging.info(flowerName + "的花语未收录")
        return "人家不知道" +flowerName + "的花语了啦"
    else:
        result = ""
        for flower in flowerList:
            result = result + flower.description()
        if len(flowerList)>10:
            result = result + u"\n★更新姬提示★:\n搜索的结果有点多,可以尝试更多关键字搜索哦!"
        return result
   
    