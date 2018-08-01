<#include  "parts/security.ftl" />
<#import "parts/common.ftl" as S>

<@S.page>
<h2 class="text-center">${userChannel.username}</h2>
<#if !isCurrentUser>
    <#if isSubscribe>
    <a class="btn btn-outline-danger m-3"  href="/Users/unsubscribe/${userChannel.id}"> Unsubscribe </a>
    <#else>
    <a class="btn btn-outline-success m-3" href="/Users/subscribe/${userChannel.id}"> Subscribe </a>
    </#if>
</#if>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <div class="card-title"> Subscriptions </div>
                    <h3 class = "card-text">
                        <a href="/Users/subscriptions/${userChannel.id}/list">${subscriptionsCount}</a>
                    </h3>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <div class="card-title"> Subscribers </div>
                    <h3 class="card-text">
                        <a href="/Users/subscriber/${userChannel.id}/list">${subscriberCount}</a>
                    </h3>
                </div>
            </div>
        </div>
    </div>
</div>
<#if isCurrentUser || isAdmin>
    <#include "parts/messageEdit.ftl"/>
</#if>
    <#include "parts/messageList.ftl" />
</@S.page>