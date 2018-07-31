<#include  "parts/security.ftl" />
<#import "parts/common.ftl" as S>

<@S.page>
<#if isCurrentUser || isAdmin>
    <#include "parts/messageEdit.ftl"/>
</#if>
    <#include "parts/messageList.ftl" />
</@S.page>