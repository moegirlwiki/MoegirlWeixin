# coding: utf-8
'''
Created on 2013-8-7
search the moegirl wiki
@author: xuechong
'''
import MoeGirlWiki
import Weixin
import urllib 

__suf__="是什么"

class SearchHandler():
    @staticmethod
    def __helpkey__ ():
        return "搜索"
    @staticmethod
    def __helpcontent__():
        return "输入'**是什么'就可以搜索萌百内对应的内容咯\n比如'夏娜是什么'就可以搜索'夏娜'相关的条目哦!"
    
    def handle(self,handlerChain):
        
        if (handlerChain.getMsgType()=="text"):
            content = handlerChain.getMsgContent()
            if hasKeyWord(content):
                searchKey = content[:content.rfind(__suf__)]
                resultList = MoeGirlWiki.searchTitle(searchKey)
                return Weixin.textReply(handlerChain.userMsg,buildReplyStr(resultList))
        return handlerChain.invokeNext()
    
def hasKeyWord(content):
    return (content !="" and content is not None) and content.endswith(__suf__)
    

def buildReplyStr(wikiList):
    if len(wikiList)>0:
        result = list()
        for subject in  wikiList:
            result.append("\n☆")
            result.append(subject.title.encode("utf-8"))
            result.append(":\n")
            result.append(subject.snippet.encode("utf-8"))
            result.append("\n")
            result.append("http://zh.moegirl.org/" + str(urllib.quote(subject.title.encode("utf-8"))))
            result.append("\n\n")
        if len(wikiList)>5:
            result.append("\n★更新姬提示★:\n搜索的结果有点多,可以尝试更多关键字搜索哦!")
        return ("").join(result)
    else:
        return "人家不知道这种东西了啦"
        
    
    