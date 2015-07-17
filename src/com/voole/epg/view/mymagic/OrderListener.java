package com.voole.epg.view.mymagic;

import com.voole.epg.corelib.model.account.Product;
import com.voole.epg.view.mymagic.OrderView.ProductItemView.OrderStatus;

public interface OrderListener {
	public void order(Product product, OrderStatus orderStatus);
}
