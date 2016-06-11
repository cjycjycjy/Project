package ddoraemi.detailedgroupinfo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class CalWidthGridView extends GridView{
	 private int mRequestedNumColumns = 0;

	    public CalWidthGridView(Context context) {
	        super(context);
	    }

	    public CalWidthGridView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	    }

	    public CalWidthGridView(Context context, AttributeSet attrs, int defStyle) {
	        super(context, attrs, defStyle);
	    }

	    @Override
	    public void setNumColumns(int numColumns) {
	        super.setNumColumns(numColumns);

	        if (numColumns != mRequestedNumColumns) {
	            mRequestedNumColumns = numColumns;
	        }
	    }

	    @Override
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	        if (mRequestedNumColumns > 0) {
	            int width = (mRequestedNumColumns * getColumnWidth())
	                    + ((mRequestedNumColumns-1) * getHorizontalSpacing())
	                    + getListPaddingLeft() + getListPaddingRight();

	            setMeasuredDimension(width, getMeasuredHeight());    
	        }
	    }
}
