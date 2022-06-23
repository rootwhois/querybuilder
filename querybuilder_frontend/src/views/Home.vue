<template>
  <div class="home">
    <vue-query-builder :rules="rules" v-model="query"></vue-query-builder>
    <button @click="getQuery">获取Query</button>
    <button @click="getSql(query)">获取SQL</button>
  </div>
</template>

<script>
import VueQueryBuilder from "vue-query-builder";

export default {
  name: "Home",
  components: { VueQueryBuilder },
  data() {
    return {
      rules: [
        {
          type: "text",
          id: "vegetable",
          label: "Vegetable",
        },
        {
          type: "text",
          id: "fruit",
          label: "Fruit",
        },
      ],

      query: {
        logicalOperator: "all",
        children: [
          {
            type: "query-builder-group",
            query: {
              logicalOperator: "any",
              children: [
                {
                  type: "query-builder-rule",
                  query: {
                    rule: "vegetable",
                    selectedOperator: "contains",
                    selectedOperand: "Vegetable",
                    value: null,
                  },
                },
                {
                  type: "query-builder-rule",
                  query: {
                    rule: "fruit",
                    selectedOperand: "Fruit",
                    value: null,
                  },
                },
              ],
            },
          },
        ],
      },
    };
  },
  methods: {
    getQuery() {
      console.log(this.query);
    },
    queryToSql(query) {
      var sql = [];
      var that = this;
      var logicalOperator = query.logicalOperator;
      var children = query.children;
      children.forEach((child) => {
        var type = child.type;
        if (type === "query-builder-rule") {
          sql.push(child.query.rule);
          sql.push(child.query.operator);
          sql.push(child.query.value);
        } else {
          sql.push("(");
          sql.push(that.queryToSql(child.query));
          sql.push(")");
        }
        sql.push(logicalOperator);
      });
      sql.splice(sql.length - 1, sql.length);
      return sql.join(" ");
    },
    getSql(query) {
      this.$axios.post("/api/query/getSql", {
        query: query
      }).then(response => {
       let sql = response.data;
       console.log(sql);
       alert(sql);
      }
      );
    }
  },
};
</script>

<style>
</style>