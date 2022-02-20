create table if not exists `order`
(
	order_id varchar(32) not null comment '订单ID'
		primary key,
	order_total_amount decimal(13,2) not null comment '订单总金额',
	fee decimal not null comment '运费金额',
	receipt_flag varchar(20) not null comment '是否支持开发票，supported-支持，unsupported',
	receipt_id varchar(32) null comment '发票id',
	address varchar(300) null comment '收货地址',
	logistics_id varchar(32) null comment '物流编号',
	pay_channel varchar(10) null comment '支付渠道，bank_card、alipay、wechat_pay',
	pay_id varchar(32) null comment '支付流水号',
	status varchar(10) null comment '订单状态，done,unpay,pay,cancel,send,receive,returning,returned',
	create_date varchar(10) not null comment '创建日期，yyyy/MM/dd',
	create_time varchar(8) not null comment '创建时间，hh:mm:ss'
)
comment '订单表';

create table if not exists order_product
(
	order_id varchar(32) not null,
	product_id varchar(32) not null,
	product_name varchar(300) not null,
	product_price decimal(13,2) not null comment '产品单价',
	product_num int not null comment '产品数量',
	primary key (order_id, product_id)
)
comment '订单产品表';

create table if not exists product
(
	product_id varchar(32) not null comment '产品ID'
		primary key,
	product_name varchar(32) not null,
	price decimal(13,2) not null comment '价格'
)
comment '产品表';

create table if not exists user
(
	user_id varchar(32) not null comment '用户id'
		primary key,
	user_name varchar(300) not null comment '用户名称',
	mobile varchar(20) not null comment '用户手机号',
	sex varchar(10) not null comment '性别，male、female'
)
comment '用户表';

