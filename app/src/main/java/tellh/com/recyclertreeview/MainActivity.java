package tellh.com.recyclertreeview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import tellh.com.recyclertreeview.bean.BlockData;
import tellh.com.recyclertreeview.bean.Dir;
import tellh.com.recyclertreeview.bean.File;
import tellh.com.recyclertreeview.viewbinder.BlockNodeBinder;
import tellh.com.recyclertreeview.viewbinder.DirectoryNodeBinder;
import tellh.com.recyclertreeview.viewbinder.FileNodeBinder;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewAdapter;

public class MainActivity extends AppCompatActivity implements BlockNodeBinder.BlockClickListeners{

    private static final String TAG = "MainActivity";
    private RecyclerView rv;
    private TreeViewAdapter adapter;
    private List<TreeNode> nodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        /*nodes = new ArrayList<>();
        TreeNode<Dir> app = new TreeNode<>(new Dir("app"));
        nodes.add(app);
        TreeNode<Dir> manifest =  new TreeNode<>(new Dir("manifests"))
                .addChild(new TreeNode<>(new File("AndroidManifest.xml")));
        app.addChild(
               manifest
        );
        app.addChild(
                new TreeNode<>(new Dir("java")).addChild(
                        new TreeNode<>(new Dir("tellh")).addChild(
                                new TreeNode<>(new Dir("com")).addChild(
                                        new TreeNode<>(new Dir("recyclertreeview"))
                                                .addChild(new TreeNode<>(new File("Dir")))
                                                .addChild(new TreeNode<>(new File("DirectoryNodeBinder")))
                                                .addChild(new TreeNode<>(new File("File")))
                                                .addChild(new TreeNode<>(new File("FileNodeBinder")))
                                                .addChild(new TreeNode<>(new File("TreeViewBinder")))
                                ).addChild( new TreeNode<>(new Dir("tellefsjlhe.vf hwask.fuk we"))
                                        .addChild( new TreeNode<>(new Dir("tellefsjlhfsonfdkjnsjhdfgkhbkghbkhsgjhbkgbkehrbgh"))
                                                .addChild( new TreeNode<>(new Dir("tellefsjlhdjbfhabfksjk;anksebho;bweskdfn;karns.fbk"))
                                                        .addChild( new TreeNode<>(new Dir("tellefsjlhfnsdfnk;njkdsfngkjds"))
                                                                .addChild( new TreeNode<>(new Dir("tellefsjlhnksadfjlfsgjbggkjnrdkf;gnkjdnkgjnksrebg;kesrk;hgk;hekh;gk;he")))))))

                        )
                )
        );
        TreeNode<Dir> res = new TreeNode<>(new Dir("res"));
        nodes.add(res);
        res.addChild(
                new TreeNode<>(new Dir("layout")).lock() // lock this TreeNode
                        .addChild(new TreeNode<>(new File("activity_main.xml")))
                        .addChild(new TreeNode<>(new File("item_dir.xml")))
                        .addChild(new TreeNode<>(new File("item_file.xml")))
        );
        res.addChild(
                new TreeNode<>(new Dir("mipmap"))
                        .addChild(new TreeNode<>(new File("ic_launcher.png")))
        );*/
        nodes = new ArrayList<>();
        TreeNode<BlockData>  titleBlock= new TreeNode<>(new BlockData("App Block"));
        TreeNode<BlockData>  triggerBlock= new TreeNode<>(new BlockData("Trigger Block"));
        TreeNode<BlockData>  webHookBlock= new TreeNode<>(new BlockData("Webhook Block"));
        TreeNode<BlockData>  ifBlock = new TreeNode<>(new BlockData("If Block"));
        TreeNode<BlockData>  forEachBlock = new TreeNode<>(new BlockData("For each block"));
        TreeNode<BlockData>  innerBlock1 = new TreeNode<>(new BlockData("inner Block 1"));
        TreeNode<BlockData>  innerBlock2 = new TreeNode<>(new BlockData("inner Block 2"));
        TreeNode<BlockData>  innerBlock3 = new TreeNode<>(new BlockData("inner Block 3"));

        nodes.add(titleBlock);
        nodes.add(triggerBlock);
        nodes.add(ifBlock);
        ifBlock.addChild(webHookBlock);
        ifBlock.addChild(forEachBlock);
        forEachBlock.addChild(innerBlock1);
        forEachBlock.addChild(innerBlock2);
        ifBlock.addChild(innerBlock3);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv);

        rv.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    Log.e("TAG", "meet a IOOBE in RecyclerView");
                }
            }

            @Override
            public boolean supportsPredictiveItemAnimations() {
                return true;
            }
        });
        setupAdapter();


    }


    private void setupAdapter() {
        adapter = new TreeViewAdapter(nodes, Arrays.asList(new FileNodeBinder(), new DirectoryNodeBinder(),
                new BlockNodeBinder(this)));
        // whether collapse child nodes when their parent node was close.
//        adapter.ifCollapseChildWhileCollapseParent(true);
        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
//                if (!node.isLeaf()) {
//                    //Update and toggle the node.
//                    onToggle(!node.isExpand(), holder);
////                    if (!node.isExpand())
////                        adapter.collapseBrotherNode(node);
//                }
                return false;
            }

            @Override
            public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
//                DirectoryNodeBinder.ViewHolder dirViewHolder = (DirectoryNodeBinder.ViewHolder) holder;
//                final ImageView ivArrow = dirViewHolder.getIvArrow();
//                int rotateDegree = isExpand ? 90 : -90;
//                ivArrow.animate().rotationBy(rotateDegree)
//                        .start();
            }
        });
        adapter.setHasStableIds(true);
        rv.setAdapter(adapter);
        adapter.expandNodes(nodes);

    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.id_action_close_all:
//                adapter.notifyDataSetChanged();
//                TreeNode resNode = nodes.get(1);
//                nodes.remove(1);
//                TreeNode manifestNode = ((TreeNode)nodes.get(0).getChildList().get(0));
//                manifestNode.setParent(null);
//                nodes.add(manifestNode);
//                nodes.get(0).setChildAtPos(0,resNode);


//                setupAdapter();
//                adapter.collapseAll();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP
            | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END,0) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            Log.d(TAG, "onMove: fromPosition"+fromPosition+" toPosition"+ toPosition);
//            TreeNode resNode = nodes.get(1);
//            nodes.remove(1);
//            TreeNode manifestNode = ((TreeNode)nodes.get(0).getChildList().get(0));
//            manifestNode.setParent(null);
//            nodes.add(manifestNode);
//            nodes.get(0).setChildAtPos(0,resNode);
            Collections.swap(nodes,fromPosition,toPosition);
//            adapter.notifyItemMoved(fromPosition,toPosition);
            adapter.refresh(nodes);
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };

    @Override
    public void onAddBlockClick(BlockData blockData,TreeNode node) {
        TreeNode<BlockData>  newBlock= new TreeNode<>(new BlockData("new Block"));
        if (node.getParent()==null) {
            nodes.add(newBlock);
        } else {
            node.getParent().addChild(newBlock);
        }
        adapter.refresh(nodes);
    }

    @Override
    public void onAddChildClick(BlockData blockData,TreeNode node) {
        TreeNode<BlockData>  newBlock= new TreeNode<>(new BlockData("new Block"));
        node.addChild(newBlock);
        adapter.refresh(nodes);
        adapter.expandAllNodes(node);
    }
}

class WrapContentLinearLayoutManager extends LinearLayoutManager {
    public WrapContentLinearLayoutManager(Context context) {
        super(context);
    }

    public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public WrapContentLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            Log.e("TAG", "meet a IOOBE in RecyclerView");
        }
    }
}