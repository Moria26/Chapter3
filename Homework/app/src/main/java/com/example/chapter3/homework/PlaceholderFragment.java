package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {

    private LottieAnimationView loadingView;
    private ListView friendList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);

        loadingView = view.findViewById(R.id.loading_view);

        friendList = view.findViewById(R.id.friend_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, new String[]{"Jack","Tom","Moria","Sue"});
        friendList.setAdapter(adapter);
        friendList.setAlpha(0f);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(loadingView, "alpha", 1f, 0f);
                fadeOut.setDuration(500);
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(friendList, "alpha", 0f, 1f);
                fadeIn.setDuration(500);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(fadeOut, fadeIn);
                animatorSet.start();
            }
        }, 5000);
    }
}
