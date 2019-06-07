package cn.edu.bnuz.bell.wx

class QuestionKey {
    /**
     * 答案
     */
    Answers answer
    /**
     * 关键词
     */
    String key
    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        answer comment: '答案'
        key   type: 'text', comment: '关键词'
    }

}
