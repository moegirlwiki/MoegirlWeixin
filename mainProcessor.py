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
        if Weixin.validate(self.request.get):
            logging.debug(self.request._body__get())
            write = self.response.out.write
            msg = MsgContent(self.request._body__get())
            handlerChain= HandlerChain(userMsg=msg)
            write(handlerChain.doChain())

app = webapp2.WSGIApplication([('/weixin', MainProcessor)])


