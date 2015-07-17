package com.voole.epg.cooperation.ali;

public class YunOSOrderManager {
	
	private String Order = null;
	private String Sign = null;
	

	public void GenerateOrder(String prikey,String orderFromApp)
	{
		Order = orderFromApp;
		Sign = RSASign.sign(Order, prikey, "utf-8" );
	}
	
	public void GenerateOrder(String prikey,String partner,String subject_id,String subject,String price
			,String partner_notify_url,String partner_order_no)
	{
	
		Order = "partner="+partner+"&subject_id="+subject_id+"&subject="+subject+"&price="+price+"&partner_notify_url="+partner_notify_url+"&partner_order_no="+partner_order_no;
		Sign = RSASign.sign(Order, prikey, "utf-8" );
	}

	public String getOrder()
	{
		return Order;
	}
	
	public String getSign()
	{
		return Sign;
	}
}
