spring:
  application:
    name: ${projectNameUnderLine}
  mvc:
    pathmatch:
      #配合swagger文档做出的配置
      matching-strategy: ant_path_matcher
  profiles:
    active: dev

