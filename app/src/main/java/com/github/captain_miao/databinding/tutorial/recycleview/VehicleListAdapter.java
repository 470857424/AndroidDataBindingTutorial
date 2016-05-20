package com.github.captain_miao.databinding.tutorial.recycleview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.captain_miao.databinding.tutorial.BR;
import com.github.captain_miao.databinding.tutorial.R;
import com.github.captain_miao.databinding.tutorial.model.VehicleInfo;
import com.github.captain_miao.recyclerviewutils.BaseWrapperRecyclerAdapter;

import java.util.List;


/**
 * @author YanLu
 * @since 16/4/27
 */
public class VehicleListAdapter extends BaseWrapperRecyclerAdapter<VehicleInfo, RecyclerView.ViewHolder> {

    public VehicleListAdapter() {
    }

    public VehicleListAdapter(List<VehicleInfo> items) {
        addAll(items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_view, parent, false);

        return new VehicleListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final VehicleInfo info = getItem(position);
        if(holder instanceof ViewHolder){
            ViewDataBinding binding = ((ViewHolder) holder).getBinding();
            binding.setVariable(BR.info, info);
            binding.setVariable(BR.itemCLick, itemListener);
            binding.setVariable(BR.selectedCLick, selectedListener);
            binding.executePendingBindings();
        }
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }
    }

    OnClickVehicleItem itemListener = new OnClickVehicleItem<VehicleInfo>() {
        @Override
        public void onClick(View v, VehicleInfo data) {
            Toast.makeText(v.getContext(), data.brand, Toast.LENGTH_SHORT).show();
        }
    };

    OnClickVehicleItem selectedListener = new OnClickVehicleItem<VehicleInfo>() {

        @Override
        public void onClick(View v, VehicleInfo data) {
            for(VehicleInfo e : getList()){
                if(e.isSelected.get()) {
                    e.isSelected.set(false);
                    break;
                }
            }
            data.isSelected.set(true);
        }


    };
}
