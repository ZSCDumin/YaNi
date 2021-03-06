package com.handsome.didi.Fragment.Home;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.handsome.didi.Adapter.Home.RechargeAdapter;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.Recharge;
import com.handsome.didi.Controller.RechargeController;
import com.handsome.didi.R;

import java.util.List;

/**
 * Created by handsome on 2016/4/7.
 */
public class RechargeGameFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private RechargeController rechargeController;

    private GridView gv_recharge;
    private RechargeAdapter rechargeAdapter;
    private List<Recharge> rechargeList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recharge_game;
    }

    @Override
    public void initViews() {
        gv_recharge = findView(R.id.gv_recharge);
    }

    @Override
    public void initData() {

        rechargeController = RechargeController.getInstance();

        initRechargeViews();
    }

    @Override
    public void initListener() {
        gv_recharge.setOnItemClickListener(this);
    }

    @Override
    public void processClick(View v) {
    }


    private void initRechargeViews() {
        rechargeController.query(Recharge.TYPE.TYPE_GAME, new BaseController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                rechargeList = (List<Recharge>) list;
                rechargeAdapter = new RechargeAdapter(getActivity(), rechargeList);
                gv_recharge.setAdapter(rechargeAdapter);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        rechargeAdapter.setSelectPosition(position);
        rechargeAdapter.notifyDataSetChanged();
    }
}
