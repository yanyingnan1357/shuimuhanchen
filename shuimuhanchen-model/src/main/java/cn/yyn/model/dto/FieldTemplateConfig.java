package cn.yyn.model.dto;

import lombok.*;

/**
 * 编辑房源信息时，暴露给前端的字段模板
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FieldTemplateConfig {
    /**
     * 自定义名称 唯一
     */
    private String id;
    /**
     * 大对象字段名
     */
    private String key;
    /**
     * 委托类型 逗号分隔
     */
    private String delTypes;

    //落地中台使用的配置字段
    /**
     * 价格类型 type字段 改价日志使用
     */
    private String priceType;

    //前台数据转换的配置字段
    /**
     * basecodeType
     */
    private String optionBaseCodeType;
    /**
     * saas配置的参数键 目前只支持系统维度
     */
    private String saasParaKey;
    /**
     * basecodeDimension 城市/公司
     */
    private String optionBaseCodeDimension;
    /**
     * 本字段不为空，则说明有级联关系，配置所在层级
     */
    private Integer optionBaseCodeLevel;

    /**
     * 下拉列表中引用的枚举
     */
    private String optionEnum;
    /**
     * 转换为存储值的表达式，当该字段为空时，直接取值；不为空时，取表达式运算后的结果
     */
    private String transform2StoreSpel;
    /**
     * 是否需要暴露给前端，结果为false则不输出该field
     */
    private String exposeSpel;
    /**
     * 判断是否必填的表达式，结果写入required属性
     */
    private String requiredSpel;
    /**
     * 判断是否可编辑的表达式，结果写入editable属性
     */
    private String editableSpel;
    /**
     * 上下文中需要调用外部系统的字段，按需调用
     */
    private String externalFields;
    /**
     * 外部系统参照值提示
     */
    private String realValueTipSpel;
    /**
     * 经纪人的值与外部系统不同时的帮助文案 - 展示用
     */
    private String helpIconTextInShowSpel;

    //前端模板字段
    /**
     * 显示条件 用于联动时判断
     */
    private String showCondition;
    /**
     * 标签
     */
    private String label;
    /**
     * 关联组件
     */
    private String relatedFields;
    /**
     * 类型
     */
    private String type;
    /**
     * 校验规则 - 正则表达式 - 校验格式
     */
    private String validateRegex;
    /**
     * 业务规则校验 - Spel表达式 - 校验业务
     */
    private String validateRuleSpel;
    /**
     * 规则校验失败原因
     */
    private String validateUnPassedReason;
    /**
     * 作为关联字段时，该字段去掉后面的逗号join 特殊：改造情况：一改二
     */
    private Boolean noCommaSuffix;
    /**
     * 显示文本的表达式，按表达式转换后显示给前端，对应value字段，如 Date - 2019-10-01 , baseCode - 001
     */
    private String transform2DisplaySpel;
    /**
     * 显示文本的表达式，按表达式转换后显示给前端，对应desc字段，默认用value填充，特殊情况下指定转换表达式，如是否满N的证件日期
     */
    private String transform2DescSpel;
    /**
     * 占位文本
     */
    private String placeholder;
    /**
     * 追加单位文本
     */
    private String appendUnit;
    /**
     * 是否可编辑
     */
    private Boolean editable;
    /**
     * 必填
     */
    private Boolean required;
    /**
     * 长度最小值
     */
    private Integer minLength;
    /**
     * 长度最大值
     */
    private Integer maxLength;
    /**
     * 最小值
     */
    private String minValueSpel;
    /**
     * 最大值
     */
    private String maxValueSpel;
    /**
     * 小数位数
     */
    private Integer digitLength;
    /**
     * 帮助文案
     */
    private String helpText;
    /**
     * 不可编辑原因文案
     */
    private String unEditableReason;
}
