<#include "security.ftl"/>
<#import "login.ftl" as L>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
    <a class="navbar-brand" href="/">projectMVC</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ">
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="/main">Main <span class="sr-only">(current)</span></a>
            </li>
            <#if isAdmin>
            <li class="nav-item ">
                <a class="nav-link" href="/Users">Users <span class="sr-only">(current)</span></a>
            </li>
            </#if>
            <li class="nav-item ">
                <a class="nav-link" href="/login">Sing in <span class="sr-only">(current)</span></a>
            </li>
            <#if user??>
            <li class="nav-item ">
                <a class="nav-link" href="/Users/profile">Profile<span class="sr-only">(current)</span></a>
            </li>
            </#if>
            <#if !known>
            <li class="nav-item ">
                <a class="nav-link" href="/registration">Registration <span class="sr-only">(current)</span></a>
            </li>
            </#if>
            <#if user??>
            <li class="nav-item ">
                <a class="nav-link" href="/messages/${UserId}">My messages<span class="sr-only">(current)</span></a>
            </li>
            </#if>
        </ul>
        <div class="navbar-text mr-2">${Username}</div>
        <@L.logout />
    </div>
</nav>
