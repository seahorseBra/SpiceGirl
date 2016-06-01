package view;

import javaBean.ImageTypeResult;
import javaBean.PrettyGrilImage;

/**
 * Created by zchao on 2016/6/1.
 */
public interface IMainView extends IBaseView {
    void addAllImageType(ImageTypeResult.ShowapiResBodyBean.ListBean listBean );
    void failMassage(String msg);
    void getMoreData();
    void reFreshData();
    void addPrettyGirlDate(PrettyGrilImage.ShowapiResBodyBean.PagebeanBean pagebeanBean);
}
