# coding: utf-8
'''
Created on 2013-7-5
@author: xuechong
'''

from bs4 import BeautifulSoup
from utils.Commons import fetchContentFromUrl 
from utils.Commons import todayStr
from Weixin import textReply
from model.Dictionarys import findDictByName
from utils.Commons import loadFromMemcache

__animeListKey__="新番列表"

class AnimeListHandler():
    
    @staticmethod
    def __helpkey__ ():
        return __animeListKey__
    
    @staticmethod
    def __helpcontent__():
        return "输入'新番列表'就可以查看本周的新番列表哦"
    
    def handle(self,handlerChain):
        if handlerChain.getMsgContent() == __animeListKey__:
            return textReply(handlerChain.userMsg,todayContent())
        else:
            return handlerChain.invokeNext()


def getContent ():
    url = findDictByName("animeurl").content
    return fetchContentFromUrl(url)

def formatContent(content):
    result= ""
    soup = BeautifulSoup(content)
    _dtList = soup.find_all("dt")
    for dl in soup.find_all("dl"):
        result = result + "\r\n★" + dl.find("dt").contents[0].encode("utf-8") + ":\r\n"
        for dd in dl.find_all("dd"):
            result = result +  dd.find("a").contents[0].encode("utf-8") + "\r\n"
    return result
            
def todayContent():
    return loadFromMemcache("animeList", todayKey(), lambda :formatContent(getContent()))

todayKey = lambda : "animeList" + str(todayStr())

