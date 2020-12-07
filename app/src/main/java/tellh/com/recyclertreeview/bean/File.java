package tellh.com.recyclertreeview.bean;

import tellh.com.recyclertreeview.R;
import tellh.com.recyclertreeview_lib.LayoutItemType;

/**
 * Created by tlh on 2016/10/1 :)
 */

public class File implements LayoutItemType {
    public String name;

    public File(String fileName) {
        this.name = fileName;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_file;
    }
}
