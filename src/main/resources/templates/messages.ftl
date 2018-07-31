<#import "parts/common.ftl" as S>

<@S.page>
<#if isCurrentUser>
    <#include "parts/messageEdit.ftl"/>
</#if>
    <#include "parts/messageList.ftl" />
</@S.page>