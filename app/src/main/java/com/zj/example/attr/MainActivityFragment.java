package com.zj.example.attr;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * 1.代碼中獲取attr屬性的值
 * 2.獲取style中的值
 *
 * create by zhengjiong
 * Date: 2015-04-19
 * Time: 17:15
 */
public class MainActivityFragment extends Fragment {
    private static final String TAG = "MainActivityFragment";

    private View mRootView;
    private TextView mTxtValue;
    private TextView mTxtValue2;

    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_main, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTxtValue = (TextView) mRootView.findViewById(R.id.txt_value);
        mTxtValue2 = (TextView) mRootView.findViewById(R.id.txt_value2);


        TypedValue typedValue = new TypedValue();

        /**
         * 使用resolveAttribute獲取colorPrimary的值
         */
        getActivity().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);

        int data = typedValue.data;
        int resourceId = typedValue.resourceId;

        StringBuffer content = new StringBuffer();
        content.append("獲取colorPrimary data=");
        content.append(data);
        content.append(",并設置為文字顏色");
        content.append("\n\n\n");

        content.append("resourceId=");
        content.append(resourceId);

        mTxtValue.setText(content.toString());
        mTxtValue.setTextColor(data);


        /**
         * 獲取Style中attr的值
         * 方法1:
         *
         * Return a TypedArray holding the values defined by the style
         * resource resid which are listed in attrs.
         * 返回AppTheme中設置的MyStyle中屬性列表
         */
        //int[] attribute = new int[] { R.attr.colorPrimary, R.attr.attr1 };
        TypedArray typedArray = getActivity().getTheme().obtainStyledAttributes(R.style.AppTheme, R.styleable.MyStyle);

        //int colorPrimary = array.getColor(0, -1);
        /**
         * 可以使用getString(0),用0代表獲取attr中的第一個屬性,
         * 也可以直接寫屬性的名字R.styleable.MyStyle_attr1
         */
        //String attr = typedArrayAttr1.getString(0);
        String attr = typedArray.getString(R.styleable.MyStyle_attr1);
        typedArray.recycle();

        /**
         * 獲取Style中attr的值
         * 方法2:
         */
        TypedValue typedArrayAttr2 = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.attr1, typedArrayAttr2, true);

        mTxtValue.append(",\n"+ "attr= " + attr);
        mTxtValue.append(",\n" + "attr= " + typedArrayAttr2.string);

    }
}
