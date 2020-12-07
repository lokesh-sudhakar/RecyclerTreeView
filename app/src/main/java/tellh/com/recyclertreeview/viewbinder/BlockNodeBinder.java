package tellh.com.recyclertreeview.viewbinder;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import tellh.com.recyclertreeview.R;
import tellh.com.recyclertreeview.bean.BlockData;
import tellh.com.recyclertreeview.bean.Dir;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewBinder;

/**
 * @author Lokesh chennamchetty
 * @date 23/11/2020
 */
public class BlockNodeBinder extends TreeViewBinder<BlockNodeBinder.ViewHolder> {

  public BlockClickListeners blockClickListeners;
  private TreeNode node;

  @Override
  public ViewHolder provideViewHolder(View itemView) {
    return new BlockNodeBinder.ViewHolder(itemView);
  }

  public BlockNodeBinder (BlockClickListeners listener) {
    this.blockClickListeners = listener;
  }

  public interface BlockClickListeners {
    void onAddBlockClick(BlockData blockData,TreeNode node);
    void onAddChildClick(BlockData blockData,TreeNode node);
  }

  @Override
  public void bindView(ViewHolder holder, int position, final TreeNode node) {
    this.node = node;
    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
            /*width*/ getScreenWidth(),
            /*height*/ ViewGroup.LayoutParams.WRAP_CONTENT);
    holder.itemView.setLayoutParams(param);
    final BlockData blockData = (BlockData) node.getContent();
    holder.flowNameTv.setText(blockData.name);
    holder.addChild.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        blockClickListeners.onAddChildClick(blockData,node);
      }
    });
    holder.addBlock.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        blockClickListeners.onAddBlockClick(blockData,node);
      }
    });
  }

  public static int getScreenWidth() {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }

  @Override
  public int getLayoutId() {
    return R.layout.layout_workflow_block;
  }


  public static class ViewHolder extends TreeViewBinder.ViewHolder {

    TextView flowNameTv;
    Button addBlock;
    Button addChild;

    public ViewHolder(View rootView) {
      super(rootView);
      this.flowNameTv = rootView.findViewById(R.id.flowNameTv);
      this.addBlock = rootView.findViewById(R.id.addBlock);
      this.addChild = rootView.findViewById(R.id.addChild);
    }


  }
}
