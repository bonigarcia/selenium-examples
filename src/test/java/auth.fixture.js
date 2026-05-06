"const { test: base, expect } = require('@playwright/test');
// edit
const test = base.extend({
  authenticatedPage: async ({ page