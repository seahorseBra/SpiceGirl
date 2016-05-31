package javaBean;

import java.util.List;

/**
 * Created by zchao on 2016/5/31.
 */
public class ImageTypeResult {


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"list":[{"list":[{"id":4001,"name":"清纯"},{"id":4002,"name":"气质"},{"id":4003,"name":"萌女"},{"id":4004,"name":"校花"},{"id":4005,"name":"婚纱"},{"id":4006,"name":"街拍"},{"id":4007,"name":"非主流"},{"id":4008,"name":"美腿"},{"id":4009,"name":"性感"},{"id":4010,"name":"车模"},{"id":4011,"name":"男色图片"},{"id":4012,"name":"模特美女"},{"id":4013,"name":"美女魅惑"},{"id":4014,"name":"日韩美女"}],"name":"美女图片"}],"ret_code":0}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * list : [{"list":[{"id":4001,"name":"清纯"},{"id":4002,"name":"气质"},{"id":4003,"name":"萌女"},{"id":4004,"name":"校花"},{"id":4005,"name":"婚纱"},{"id":4006,"name":"街拍"},{"id":4007,"name":"非主流"},{"id":4008,"name":"美腿"},{"id":4009,"name":"性感"},{"id":4010,"name":"车模"},{"id":4011,"name":"男色图片"},{"id":4012,"name":"模特美女"},{"id":4013,"name":"美女魅惑"},{"id":4014,"name":"日韩美女"}],"name":"美女图片"}]
     * ret_code : 0
     */

    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        private int ret_code;
        /**
         * list : [{"id":4001,"name":"清纯"},{"id":4014,"name":"日韩美女"}]
         * name : 美女图片
         */

        private List<ListBean> list;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String name;
            /**
             * id : 4001
             * name : 清纯
             */

            private List<ImageType> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ImageType> getList() {
                return list;
            }

            public void setList(List<ImageType> list) {
                this.list = list;
            }


            public static class ImageType {
                private int id;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
