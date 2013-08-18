# coding: utf-8
'''
Created on 2013-8-7
http://zh.moegirl.org/api.php?format=json&action=query&list=search&srwhat=title&srsearch=%E5%A4%8F%E5%A8%9C&srlimit=10
http://zh.moegirl.org/api.php?format=json&action=query&list=search&srwhat=text&srsearch=%E5%A8%9C
@author: xuechong
'''
from utils.Commons import fetchContentFromUrl
import json
import logging
import urllib

_wikiurl = "http://zh.moegirl.org/api.php?format=json&action=query&list=search&srwhat=title&srsearch=$title&srlimit=$limit"

cleancontent = lambda x:x.replace("<span class='searchmatch'>", "").replace("</span>", "")


class WikiContent(object):
    
    title=""
    snippet=""
    size=""
    wordcount=""
    timestamp=""
    
def searchTitle(title,size=10):
    result = list()
    url = _wikiurl.replace("$title", urllib.quote(title)).replace("$limit", "10")
    logging.info(url)
    remoteResult = fetchContentFromUrl(url)
    
    allSubjects=json.loads(remoteResult)["query"]["search"]
    for subject in allSubjects:
        content = WikiContent()
        content.title = cleancontent(subject["title"])
        content.snippet = cleancontent(subject["snippet"])
        content.size = subject["size"]
        content.wordcount = subject["wordcount"]
        content.timestamp = subject["timestamp"]
        result.append(content)
    return result

#if __name__ == '__main__':
#    remoteResult = fetchContentFromUrl(_wikiurl.replace("$title", "夏娜").replace("$limit", "10"))
#    print remoteResult + "\n"
#    result = json.loads(remoteResult)
#    allSubjects=result["query"]["search"]
#    for subject in allSubjects:
#        print "title=" + cleancontent(subject["title"]) + "\n"
#        print "snippet=" + cleancontent(subject["snippet"]) + "\n"
#        print "timestamp=" + str(subject["timestamp"]) + "\n"
#    
#    
#    
#    
#    
#    pass
    