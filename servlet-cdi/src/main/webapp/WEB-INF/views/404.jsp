<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<c:url value="/" var="urlIndex" />
<t:template>
  <div class="row">
    <div class="col-md-12">
      <h2>404 - Page not found!</h2>
      <div class="alert alert-warning">
        <span>The requested page cannot be found.</span>
        <a href="${urlIndex}">Back to index &rarr;</a>
      </div>
    </div>
  </div>
</t:template>