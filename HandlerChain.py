# coding: utf-8
'''
Created on 2013-7-3
handler chain
@author: xuechong
'''
from moehandlers.FlowerHandler import FlowerHandler
from moehandlers.SellMoeHandler import SellMoeHandler
from moehandlers.HelpHandler import HelpHandler
from moehandlers.AnimeListHandler import AnimeListHandler
from Weixin import textReply
import logging
from moehandlers.SearchHandler import SearchHandler

__default_chain__ = (SellMoeHandler,SearchHandler,FlowerHandler,HelpHandler,AnimeListHandler)
__text_chain__=(SellMoeHandler,SearchHandler,FlowerHandler,HelpHandler,AnimeListHandler)

def textHandlerChain(userMsg):
    """
    return a new instance of a default text msg handler chain
    """
    return HandlerChain(userMsg,list(__text_chain__))

class HandlerChain(object):
    """
    the handler chain 
    call doChain to get a reply xml str
    """
    handlers = list()
    userMsg = None
    
    def __init__(self,userMsg,handlerList=None):
        self.handlers = handlerList
        if self.handlers==None:
            self.handlers=list(__default_chain__)
        logging.debug("new handlerChain" + str(self.handlers))
        self.userMsg = userMsg
        
    def doChain(self):
        """
        invoke handlers and return reply xmlstr
        """
        try :
            result =  self.invokeNext()
            return result==None and textReply(self.userMsg) or result
        except Exception as e:
            logging.exception(str(e))
            return textReply(self.userMsg,"555更新姬被玩坏了啦><")
    
    def invokeNext(self):
        """
        invoke next handler until there is no handler left or have result
        returns None if no handler can hand the income msg
        """
        result = None
        if len(self.handlers)>0:
            handler = self.handlers.pop()()
            result = handler.handle(self)
            if result==None and len(self.handlers)>0:
                result = self.invokeNext()
        return result
    
    def getMsgType(self):
        """
        get the msgtype of the income msg
        """
        return self.userMsg.get("MsgType")
    
    def getMsgContent(self):
        """
        get the content of the income msg
        """
        return self.userMsg.get("Content").encode("utf-8").strip()
    def forceStop(self):
        """
        stop the handler chain 
        after call this ,if you can return a None to reply user a default msg
        and the rest of the handlers will not be invoked
        *return a None*
        """
        self.handlers = list()
        return None
        
        