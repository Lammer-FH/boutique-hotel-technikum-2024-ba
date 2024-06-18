<template>
  <div ref="container">
    <div ref="content">
      <a @click="movePageBy(-1)">&#10094;&nbsp;</a>
      <ion-button v-if="cropBefore" shape="round" size="small">
        ...
      </ion-button>
      <ion-button v-for="(page, index) in shownPages"
            :key="index"
            shape="round"
            size="small"
            :class="buttonClass(page)"
            @click="pageObject.setCurrentPage(page)"
      >
        {{ page }}
      </ion-button>
      <ion-button v-if="cropAfter" shape="round" size="small">
        ...
      </ion-button>
      <a @click="movePageBy(1)">&nbsp;&#10095;</a>
    </div>
  </div>
</template>

<script lang="ts">
import {PropType} from "vue";

interface PageObject {
  pages: number,
  get currentPage(): number,
  setCurrentPage(page: number): void
}

export default {
  props: {
    pageObject: {
      type: Object as PropType<PageObject>,
      required: true
    }
  },
  data() {
    return {
      buttonData: {
        possibleButtons: 0
      }
    }
  },
  emits: ["pageChanged"],
  computed: {
    shownPages() {
      const pages: number[] = Array.from({length: this.pageObject.pages}, (_, i) => i + 1);

      const listLength = pages.length;
      let startIndex = Math.max(0, this.pageObject.currentPage - 3);
      let endIndex = Math.min(listLength, this.pageObject.currentPage + 4);

      if (endIndex - startIndex < 7) {
        if (startIndex === 0) {
          endIndex = Math.min(listLength, startIndex + 7);
        } else {
          startIndex = Math.max(0, endIndex - 7);
        }
      }
      if (startIndex !== 0) {
        startIndex += 1;
      }
      if (endIndex !== this.pageObject.pages) {
        endIndex -= 1;
      }

      return pages.slice(startIndex, endIndex);
    },
    cropBefore() {
      return this.shownPages.length > 0 && this.shownPages[0] !== 1;
    },
    cropAfter() {
      return this.shownPages.length > 0 && this.shownPages[this.shownPages.length - 1] !== this.pageObject.pages;
    }
  },
  methods: {
    movePageBy(value: number) {
      let newPage = (this.pageObject.currentPage - 1 + value) % this.pageObject.pages
      if (newPage < 0) {
        newPage = newPage + this.pageObject.pages;
      }
      this.pageObject.setCurrentPage(newPage + 1);
    },
    buttonClass(index: number) {
      return { active: index === (this.pageObject.currentPage) }
    }
  }
}
</script>

<style scoped lang="scss">
div {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.1rem;
  background-color: var(--ion-background-color-step-150);
}

a {
  font-size: 2rem;
  cursor: pointer;
  user-select: none;
}

ion-button {
  width: 1.8rem;
  height: 1rem;
  margin: 0.1rem;
  font-weight: bolder;
  color: white;
  --padding-start: 0;
  --padding-end: 0;

  &.active {
    --background: var(--ion-color-tertiary);
  }
}
</style>