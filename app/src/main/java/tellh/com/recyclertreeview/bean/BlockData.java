package tellh.com.recyclertreeview.bean;

import tellh.com.recyclertreeview.R;
import tellh.com.recyclertreeview_lib.LayoutItemType;

/**
 * @author Lokesh chennamchetty
 * @date 23/11/2020
 */
public class BlockData implements LayoutItemType {

  public String name;

  public String title;

  public BlockData(String name) {
    this.name = name;
  }

  @Override
  public int getLayoutId() {
    return R.layout.layout_workflow_block;
  }
}
