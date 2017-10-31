package com.example.root.newdrag;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.ImageView;


public class MainActivity extends Activity implements OnDragListener, View.OnLongClickListener {


    private ImageView dropTarget, dropped;
    private String tagDropTarget, tagDroppedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.apple).setOnLongClickListener(this);
        findViewById(R.id.bear).setOnLongClickListener(this);
        findViewById(R.id.plant).setOnLongClickListener(this);


        findViewById(R.id.bear_shadow).setOnDragListener(this);
        findViewById(R.id.plant_shadow).setOnDragListener(this);
        findViewById(R.id.apple_shadow).setOnDragListener(this);
    }


    @Override
    public boolean onLongClick(View imageView) {

        ClipData clipData = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(imageView);
        imageView.startDrag(clipData, shadowBuilder, imageView, 0);
        imageView.setVisibility(View.INVISIBLE);
        return true;
    }


    @Override
    public boolean onDrag(View receivingLayoutView, DragEvent dragEvent) {

        View draggedImageView = (View) dragEvent.getLocalState();
        // ImageView draggedImageView = (ImageView) dragEvent.getLocalState();

        switch (dragEvent.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:


                return true;

            case DragEvent.ACTION_DRAG_ENTERED:

                return true;

            case DragEvent.ACTION_DRAG_LOCATION:

                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                return true;

            case DragEvent.ACTION_DROP:

                dropTarget = (ImageView) receivingLayoutView;
                dropped = (ImageView) draggedImageView;

                tagDropTarget = (String) dropTarget.getTag();
                tagDroppedImage = (String) dropped.getTag();

                isDragMatching();
                return true;


            case DragEvent.ACTION_DRAG_ENDED:

                if (!dragEvent.getResult()) {

                    draggedImageView.setVisibility(View.VISIBLE);
                }

                return true;

            default:

                break;
        }
        return false;
    }


    private boolean isDragMatching() {

        if ((tagDropTarget != null) && (tagDropTarget.equals(tagDroppedImage))) {

            dropTarget.setImageDrawable(dropped.getDrawable());

        } else {
            return false;
        }

        return true;
    }


}
