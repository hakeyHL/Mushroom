<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function addTab(opts) {
		var t = $('#layout_center_tabs');
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		}
	}
</script>
<div id="layout_center_tabs" class="easyui-tabs" style="overflow:hidden"
	data-options="fit:true,border:false">
	<div title="首页"></div>
</div>