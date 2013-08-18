# coding: utf-8
'''
Created on 2013-7-7

@author: xuechong
'''
import random
import datetime
import urllib2
import logging
from google.appengine.api import memcache

randomFromList = lambda list_:list_[random.randint(0,len(list_)-1)]

todayStr = lambda :(datetime.datetime.utcnow() + datetime.timedelta(hours=+8)).strftime("%Y%m%d")

def fetchContentFromUrl(url):
    header ={'User-Agent':'mozilla/5.0 (windows; U; windows NT 5.1; zh-cn)'}
    req=urllib2.Request(url,None,header)
    response = urllib2.urlopen(req)
    page = response.read()
    return page

memclient = memcache.Client()

def loadFromMemcache(namespace,key,createfunction,time=24*60*60):
    """
    load or create from memcache
    createfunction should be the function that can create a new content
    """
    logging.info("load from memcache" + namespace + key)
    result = memclient.get(key=key,namespace=namespace)
    if result is None:
        logging.info("create new and put into memcache" + namespace + key)
        result = createfunction()
        memclient.set(key=key,\
                   value = result,\
                   time=time,\
               namespace=namespace)
    return result
