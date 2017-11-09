<#import "function.ftl" as func>
<#assign class=model.variables.class>
<#assign company=vars.company>
<#assign project=vars.project>
<#assign type="com."+company+".mcs.core.orm."+project+".domain."+class>
<#assign tableName=model.tableName>
<#assign colList=model.columnList>
<#assign commonList=model.commonList>
<#assign pk=func.getPk(model) >
<#assign pkType=func.getPkType(model) >
<#assign pkVar=func.getPkVar(model) >

<#-- mpper_xml -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd"> 

<mapper namespace="com.${vars.company}.mcs.core.orm.${vars.project}.mapper.${class}Mapper">

	<resultMap id="Result" type="${type}">
		<#list colList as col>
			<#if (col.isPK) >
				<id property="${col.humpColumnName?uncap_first}" column="${col.columnName}" />
			<#else>
				<result property="${col.humpColumnName?uncap_first}" column="${col.columnName}" />
			</#if>
		</#list>
	</resultMap>
	
	<sql id="columns">
		<#list colList as col>${col.columnName}<#if col_has_next>,</#if></#list>
	</sql>
	
	<sql id="dynamicWhere">
		<where>
			<#list colList as col>
				<#if (col.colType=="String")>
					<if test="@Ognl@isNotEmpty(${col.humpColumnName?uncap_first})"> AND ${col.columnName}  =<#noparse>#{</#noparse>${col.humpColumnName?uncap_first}}  </if>
				<#else>
					<if test="@Ognl@isNotEmpty(${col.humpColumnName?uncap_first})"> AND ${col.columnName}  =<#noparse>#{</#noparse>${col.humpColumnName?uncap_first}} </if>
				</#if>
			</#list>
		</where>
	</sql>

	<insert id="save" parameterType="${type}">
		INSERT INTO ${tableName}
		(<include refid="columns"/>)
		VALUES
		(<#list colList as col><#noparse>#{</#noparse>${col.humpColumnName?uncap_first}<#noparse>}</#noparse><#if col_has_next>, </#if></#list>)
	</insert>
	
	<delete id="delete" parameterType="${pkType}">
		DELETE FROM ${tableName} 
		WHERE ${pk}=<#noparse>#{</#noparse>${pk}}
	</delete>
	
	<update id="update" parameterType="${type}">
		UPDATE ${tableName} SET
		<#list commonList as col>
			${col.columnName}=<#noparse>#{</#noparse>${col.humpColumnName?uncap_first}}<#if col_has_next>, </#if>
		</#list>
		WHERE ${pk}=<#noparse>#{</#noparse>${pk}}
	</update>
	
	<select id="findOne" parameterType="${pkType}" resultMap="Result">
		SELECT <include refid="columns"/>
		FROM ${tableName}
		WHERE ${pk}=<#noparse>#{</#noparse>${pk}}
	</select>
	
	<select id="findAll" parameterType="${pkType}" resultMap="Result">
		SELECT <include refid="columns"/>
		FROM ${tableName}
		<if test="@Ognl@isNotEmpty(list)">
		WHERE ${pk} IN
		<foreach item="item" index="index" collection="list" open="(" separator="," close=")">  
				<#noparse>#{</#noparse>item}
		</foreach>
		</if>
	</select>
	
	<select id="find" parameterType="${type}" resultMap="Result">
		SELECT <include refid="columns"/>
		FROM ${tableName}
		<include refid="dynamicWhere"/>
	</select>
	
		
	<insert id="insertSelective" parameterType="${type}" >
    insert into ${tableName}
	    <trim prefix="(" suffix=")" suffixOverrides="," >
	    	<#list colList as col>
		    	<if test="@Ognl@isNotEmpty(${col.humpColumnName?uncap_first})">
		    	${col.columnName},
		    	</if>
			</#list>
	    </trim>
	    <trim prefix="values (" suffix=")" suffixOverrides="," >
	    	<#list colList as col>
		    	<if test="@Ognl@isNotEmpty(${col.humpColumnName?uncap_first})">
		    	<#noparse>#{</#noparse>${col.humpColumnName?uncap_first}<#noparse>}</#noparse>,
		    	</if>
			</#list>
    	</trim>
  </insert>
  
  <update id="updateByPrimaryKeySelective" parameterType="${type}" >
    update ${tableName}
    <set >
    	<#list commonList as col>
    		<if test="@Ognl@isNotEmpty(${col.humpColumnName?uncap_first})">
			${col.columnName}=<#noparse>#{</#noparse>${col.humpColumnName?uncap_first}<#noparse>}</#noparse>,
			</if>
		</#list>
    </set>
    <#list colList as col>
		<#if (col.isPK) >
		 WHERE ${pk}=<#noparse>#{</#noparse>${col.humpColumnName?uncap_first}}
		</#if>
	</#list>
  </update>
</mapper>
