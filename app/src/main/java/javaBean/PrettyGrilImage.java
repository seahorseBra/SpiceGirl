package javaBean;

import java.util.List;

/**
 * Created by zchao on 2016/6/1.
 */
public class PrettyGrilImage {


    public int showapi_res_code;
    public String showapi_res_error;
    public ShowapiResBodyBean showapi_res_body;


    public static class ShowapiResBodyBean {


        public PagebeanBean pagebean;
        public int ret_code;


        public static class PagebeanBean {
            public int allNum;
            public int allPages;
            public int currentPage;
            public int maxResult;
            public List<ContentlistBean> contentlist;


            public static class ContentlistBean {
                public String ct;
                public String itemId;
                public String title;
                public int type;
                public String typeName;
                public List<ListBean> list;


                public static class ListBean {
                    public String big;
                    public String middle;
                    public String small;

                }
            }
        }
    }
}
