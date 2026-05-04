"const { test: base, expect } = require('@playwright/test');

const test = base.extend({
  authenticatedPage: async ({ page