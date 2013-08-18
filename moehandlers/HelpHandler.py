# coding: utf-8
'''
Created on 2013-7-4
帮助handler
@author: xuechong
'''
import Weixin
import logging
from moehandlers.FlowerHandler import FlowerHandler
from moehandlers.AnimeListHandler import AnimeListHandler
from moehandlers.SearchHandler import SearchHandler

handlers_ = (FlowerHandler,AnimeListHandler,SearchHandler)

__help_key__ = "帮助"

__help_content__ =  "输入帮助**可以查看特定的帮助哦\n比如输入:帮助花语 就可以查看花语的帮助了啦\n更新姬在可以理解的关键词:\n"
for handler in handlers_ :
    __help_content__ = __help_content__ + "☆"\
     + handler.__helpkey__() + "\n"
    
__help_dict__ = {}
for handler in handlers_ :
    __help_dict__[handler.__helpkey__()]=handler.__helpcontent__()
    
class HelpHandler(object):
   
    def handle(self,handlerChain):
        
        if (handlerChain.getMsgType()=="text"):
            content = handlerChain.getMsgContent()
            if content.startswith(__help_key__):
                result = ""
                if content == __help_key__:
                    logging.debug("view help")
                    result = __help_content__
                else:
                    key = content.replace(__help_key__, "")
                    if key in __help_dict__:
                        result = __help_dict__[key]
                    else : return handlerChain.invokeNext()#if there is no such help
                return Weixin.textReply(handlerChain.userMsg,result);  
            
        return handlerChain.invokeNext()
    
