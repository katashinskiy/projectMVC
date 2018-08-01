<#assign known = Session.SPRING_SECURITY_CONTEXT??>
<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    Username = user.getUsername()
    isAdmin = user.isAdmin()
    UserId = user.getId()
    >

<#else >
    <#assign
    Username = "Please, sing in"
    isAdmin = false
    UserId = -1
    >

</#if>
