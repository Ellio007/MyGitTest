package com.tyh.gittest.viewpage.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyh.gittest.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author TanYanHao
 */
public class TabFragment extends Fragment {
    /**
     * Rename parameter arguments, choose names that match
     */
    private static final String ARG_PARAM1 = "title";

    private String title;

    private TextView titleTv;

    public static interface OnTitleClickListener{
        void onClick(String title);
    }

    private OnTitleClickListener onTitleClickListener;

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.onTitleClickListener = onTitleClickListener;
    }

    public TabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title title.
     * @return A new instance of fragment TabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragment newInstance(String title) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1, "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        titleTv.setText(title);

        titleTv.setOnClickListener(v -> {
            // 获取 activity对象
            // 写法1
//            ViewPageActivity activity = (ViewPageActivity) getActivity();
//            activity.changeWeChatTab("微信Changed");
            // 写法2
//            Activity activity1 = getActivity();
//            if (activity1 instanceof  ViewPageActivity){
//                ((ViewPageActivity) activity1).changeWeChatTab("s");
//            }
            if (onTitleClickListener!=null){
                onTitleClickListener.onClick("changed");
            }
        });
    }

    private void initViews(@NonNull View view) {
        titleTv = view.findViewById(R.id.tvTitle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void changeTitle(String title) {
        if (!isAdded()) {
            return;
        }
        titleTv.setText(title);
    }
}
