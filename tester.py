# coding: utf-8
'''
Created on 2013-7-1

@author: xuechong
'''
import webapp2
from Weixin import MsgContent
import logging
import Weixin
from HandlerChain import HandlerChain
from moehandlers.SellMoeHandler import SellMoeHandler
from moehandlers.FlowerHandler import FlowerHandler
from moehandlers.HelpHandler import HelpHandler
from moehandlers.AnimeListHandler import AnimeListHandler
from moehandlers.SearchHandler import SearchHandler

__test_chain__=(SellMoeHandler,SearchHandler,FlowerHandler,HelpHandler,AnimeListHandler)

class MainProcessor(webapp2.RequestHandler):
    
    def get(self):
        param = self.request.get
        write = self.response.out.write
        self.response.headers['Content-Type'] = 'text/plain'
        if Weixin.validate(param):
            write(param("echostr"))
        else:
            write("what's up man -.-?")
        
    def post(self):
        #if Weixin.validate(self.request.get):
        logging.debug(self.request._body__get())
        write = self.response.out.write
        msg = MsgContent(self.request._body__get())
        chain = list(__test_chain__)
        handlerChain= HandlerChain(userMsg=msg,handlerList=chain)
        write(handlerChain.doChain())

app = webapp2.WSGIApplication([('/test', MainProcessor)])


