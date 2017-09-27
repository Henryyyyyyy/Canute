package me.henry.canuteec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import me.henry.canutecore.delegates.CanuteDelegate;
import me.henry.canutecore.ui.recycler.MultipleFields;
import me.henry.canutecore.ui.recycler.MultipleItemEntity;
import me.henry.canuteec.detail.GoodsDetailDelegate;


public class IndexItemClickListener extends SimpleClickListener {

    private final CanuteDelegate DELEGATE;

    private IndexItemClickListener(CanuteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(CanuteDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int goodsId = entity.getField(MultipleFields.ID);
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
