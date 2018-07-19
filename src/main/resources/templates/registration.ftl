<#import "parts/common.ftl" as S>
<#import "parts/login.ftl" as L>
<@S.page>
Add new user :
${message?if_exists}
<@L.login "/registration"/>
</@S.page>

