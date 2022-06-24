<template>
  <div class="home">
    <vue-query-builder :rules="rules" v-model="query"></vue-query-builder>
    <br>
    <el-button type="primary" @click="getQuery">获取Query</el-button>
    <el-button type="primary" @click="getSql(query)">获取SQL</el-button>

    <el-dialog
  title="提示"
  :visible.sync="dialogVisible"
  width="30%"
  :before-close="handleClose">
  <span>{{data}}</span>
  <span slot="footer" class="dialog-footer">
    <!-- <el-button @click="dialogVisible = false">取 消</el-button> -->
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
</el-dialog>
  </div>
</template>

<script>
import VueQueryBuilder from "vue-query-builder";

export default {
  name: "Home",
  components: { VueQueryBuilder },
  data() {
    return {
      data: "",
      dialogVisible: false,
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
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    getQuery() {
      this.data = this.query;
      this.dialogVisible = true;
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
      this.$axios
        .post("/api/query/getSql", {
          query: query,
        })
        .then((response) => {
          this.data = response.data;
          this.dialogVisible = true;
        });
    },
  },
};
</script>

<style>
</style>