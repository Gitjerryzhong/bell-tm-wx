package cn.edu.bnuz.bell.util

class Menu {
    def menuText = '''
{
    "button":
    [
        {
            "name": "信息查询",
            "sub_button":
            [
                {
                    "type": "view",
                    "name": "综合查询",
                    "url": "http://es-test.bnuz.edu.cn/info"
                },
                {
                    "type": "view",
                    "name": "学生入口",
                    "url": "http://es-test.bnuz.edu.cn/student-menu"
                },
                {
                    "type": "view",
                    "name": "教师入口",
                    "url": "http://es-test.bnuz.edu.cn/teacher-menu"
                }
            ]
        },
        {
            "name": "教务资讯",
            "sub_button":
            [
                {
                    "type": "view",
                    "name": "常见问题",
                    "url": "http://mp.weixin.qq.com/mp/homepage?__biz=MzU4MTQ4NjgwNw==&hid=5&sn=9895e1a852c9f3dcf144ea9243e3551a&scene=18#wechat_redirect"
                },
                {
                    "type": "view",
                    "name": "教师发展",
                    "url": "http://mp.weixin.qq.com/mp/homepage?__biz=MjM5MTIzNTU1Mw==&hid=1&sn=c55d9579c0f9451f1aa02a0c3aaa481c&scene=1&devicetype=android-24&version=26060533&lang=zh_CN&nettype=cmnet&ascene=7&session_us=gh_02626a872ba5&wx_header=1&from=singlemessage"
                },
                {
                    "type": "view",
                    "name": "教学快讯",
                    "url": "http://mp.weixin.qq.com/mp/homepage?__biz=MzU4MTQ4NjgwNw==&hid=2&sn=97f5d813a8b80727f82bc17835df651c&scene=18#wechat_redirect"
                },
                {
                    "type": "view",
                    "name": "教务处网站",
                    "url": "http://jwc.bnuz.edu.cn"
                }
            ]
        },
        {
            "name": "教学系统",
            "sub_button":
            [
                {
                    "type": "view",
                    "name": "质量工程系统",
                    "url": "http://te.bnuz.edu.cn/tms/login/auth"
                },
                {
                    "type": "view",
                    "name": "网络教学平台",
                    "url": "http://eol.bnuz.edu.cn"
                },
                {
                    "type": "view",
                    "name": "考勤|借教室|免听",
                    "url": "http://tm.bnuz.edu.cn"
                },
                {
                    "type": "view",
                    "name": "教务管理系统",
                    "url": "http://es.bnuz.edu.cn"
                }
            ]
        }
    ]
}
'''

    def create(accessToken) {
        def postUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=${accessToken}"
        String result = ''
        BufferedReader reader = null

        try {
            URL url = new URL(postUrl)
            HttpURLConnection  conn = (HttpURLConnection) url.openConnection()
            conn.with {
                requestMethod = "POST"   //设置本次请求的方式 ， 默认是GET方式， 参数要求都是大写字母
                doOutput = true  //是否打开输出流， 此方法默认为false
                setRequestProperty("Connection", "Keep-Alive")
                setRequestProperty("Charset", "UTF-8")
                setRequestProperty("Content-Type", "application/json; charset=UTF-8") // 设置文件类型
                setRequestProperty("accept", "application/json")
                outputStream.write(menuText.getBytes('UTF-8'))
                println(inputStream.text)
            }
        } catch (Exception e) {
            e.printStackTrace()
        } finally {
            if (reader) {
                try {
                    reader.close()
                } catch (IOException e) {
                    e.printStackTrace()
                }
            }
        }
        return result
    }
}
